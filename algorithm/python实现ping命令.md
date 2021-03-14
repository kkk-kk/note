Ping的原理



ICMP协议格式：

![image-20200523150519259](image/image-20200523150519259.png)

ARP报文格式：

![ARP报文格式](image/image-20200523144835911.png)

op位：1表示ARP请求，2表示ARP应答，3表示RARP请求，4表示RARP应答。

校验：



sys.argv[]

[Python中 sys.argv[]的用法简明解释](https://www.cnblogs.com/aland-1415/p/6613449.html)

可以理解为列表，0储存程序本身，1以后储存参数

[Python使用struct处理二进制](http://www.cnblogs.com/gala/archive/2011/09/22/2184801.html)

struct中支持的格式：

| Format |        C Type        |       Python       | 字节数 |
| :----: | :------------------: | :----------------: | :----: |
|  `x`   |       pad byte       |      no value      |   1    |
|  `c`   |        `char`        | string of length 1 |   1    |
|  `b`   |    `signed char`     |      integer       |   1    |
|  `B`   |   `unsigned char`    |      integer       |   1    |
|  `?`   |       `_Bool`        |        bool        |   1    |
|  `h`   |       `short`        |      integer       |   2    |
|  `H`   |   `unsigned short`   |      integer       |   2    |
|  `i`   |        `int`         |      integer       |   4    |
|  `I`   |    `unsigned int`    |  integer or long   |   4    |
|  `l`   |        `long`        |      integer       |   4    |
|  `L`   |   `unsigned long`    |        long        |   4    |
|  `q`   |     `long  long`     |        long        |   8    |
|  `Q`   | `unsigned long long` |        long        |   8    |
|  `f`   |       `float`        |       float        |   4    |
|  `d`   |       `double`       |       float        |   8    |
|  `s`   |       `char[]`       |       string       |   1    |
|  `p`   |       `char[]`       |       string       |   1    |
|  `P`   |      `void  *`       |        long        |        |

注1.q和Q只在机器支持64位操作时有意思

注2.每个格式前可以有一个数字，表示个数

注3.s格式表示一定长度的字符串，4s表示长度为4的字符串，但是p表示的是pascal字符串

注4.P用来转换一个指针，其长度和机器字长相关

注5.最后一个可以用来表示指针类型的，占4个字节

| Character |       Byte order       |   Size and alignment    |
| :-------: | :--------------------: | :---------------------: |
|    `@`    |         native         | native      凑够4个字节 |
|    `=`    |         native         | standard    按原字节数  |
|    `<`    |     little-endian      | standard    按原字节数  |
|    `>`    |       big-endian       | standard    按原字节数  |
|    `!`    | network (= big-endian) | standard    按原字节数  |

```
#!/usr/bin/python3.6.4
#!coding:utf-8
__author__ = 'Rosefinch'
__date__ = '2018/5/31 22:27'

import time
import struct
import socket
import select
import sys

def chesksum(data):
    """
    校验
    """
    n = len(data)
    m = n % 2
    sum = 0 
    for i in range(0, n - m ,2):
        sum += (data[i]) + ((data[i+1]) << 8)#传入data以每两个字节（十六进制）通过ord转十进制，第一字节在低位，第二个字节在高位
    if m:
        sum += (data[-1])
    #将高于16位与低16位相加
    sum = (sum >> 16) + (sum & 0xffff)
    sum += (sum >> 16) #如果还有高于16位，将继续与低16位相加
    answer = ~sum & 0xffff
    #主机字节序转网络字节序列（参考小端序转大端序）
    answer = answer >> 8 | (answer << 8 & 0xff00)
    return answer 

    '''
    连接套接字,并将数据发送到套接字
    '''
def raw_socket(dst_addr,imcp_packet):
    rawsocket = socket.socket(socket.AF_INET,socket.SOCK_RAW,socket.getprotobyname("icmp"))
    send_request_ping_time = time.time()
    #send data to the socket
    rawsocket.sendto(imcp_packet,(dst_addr,80))
    return send_request_ping_time,rawsocket,dst_addr

    '''
    request ping
    '''
def request_ping(data_type,data_code,data_checksum,data_ID,data_Sequence,payload_body):
    #把字节打包成二进制数据
    imcp_packet = struct.pack('>BBHHH32s',data_type,data_code,data_checksum,data_ID,data_Sequence,payload_body)
    icmp_chesksum = chesksum(imcp_packet)#获取校验和
    imcp_packet = struct.pack('>BBHHH32s',data_type,data_code,icmp_chesksum,data_ID,data_Sequence,payload_body)
    return imcp_packet
    '''
    reply ping
    '''
def reply_ping(send_request_ping_time,rawsocket,data_Sequence,timeout = 2):
    while True:
        started_select = time.time()
        what_ready = select.select([rawsocket], [], [], timeout)
        wait_for_time = (time.time() - started_select)
        if what_ready[0] == []:  # Timeout
            return -1
        time_received = time.time()
        received_packet, addr = rawsocket.recvfrom(1024)
        icmpHeader = received_packet[20:28]
        type, code, checksum, packet_id, sequence = struct.unpack(
            ">BBHHH", icmpHeader
        )
        if type == 0 and sequence == data_Sequence:
            return time_received - send_request_ping_time
        timeout = timeout - wait_for_time
        if timeout <= 0:
            return -1

    '''
    实现 ping 主机/ip
    '''
def ping(host):
    data_type = 8 # ICMP Echo Request
    data_code = 0 # must be zero
    data_checksum = 0 # "...with value 0 substituted for this field..."
    data_ID = 0 #Identifier
    data_Sequence = 1 #Sequence number
    payload_body = b'abcdefghijklmnopqrstuvwabcdefghi' #data
    dst_addr = socket.gethostbyname(host)#将主机名转ipv4地址格式，返回以ipv4地址格式的字符串，如果主机名称是ipv4地址，则它将保持不变
    print("正在 Ping {0} [{1}] 具有 32 字节的数据:".format(host,dst_addr))
    for i in range(0,4):
        icmp_packet = request_ping(data_type,data_code,data_checksum,data_ID,data_Sequence + i,payload_body)
        send_request_ping_time,rawsocket,addr = raw_socket(dst_addr,icmp_packet)
        times = reply_ping(send_request_ping_time,rawsocket,data_Sequence + i)
        if times > 0:
            print("来自 {0} 的回复: 字节=32 时间={1}ms".format(addr,int(times*1000)))
            time.sleep(0.7)
        else:
            print("请求超时。")

if __name__ == "__main__":
    if len(sys.argv) < 2:
        sys.exit('Usage: ping.py <host>')
    ping(sys.argv[1])

```

