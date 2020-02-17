https://blog.csdn.net/weixin_37887248/article/details/82178235

```
AutoField(Field)                           -- int自增列，必须填入参数 primary_key=True
BigAutoField(AutoField)    				   -- bigint自增列，必须填入参数 primary_key=True
SmallIntegerField(IntegerField)			   -- 小整数 -32768 ~ 32767
PositiveSmallIntegerField(PositiveIntegerRelDbTypeMixin, IntegerField)
                                           -- 正小整数 0 ~ 2147483647
IntegerField(Field)                        -- 整数列(有符号的) -2147483648 ～ 2147483647
PositiveIntegerField(PositiveIntegerRelDbTypeMixin, IntegerField)
                                           -- 正整数 0 ～ 2147483647
BigIntegerField(IntegerField) -- 长整型(有符号的) -9223372036854775808～9223372036854775807
```

model中没有自增列，系统自动创建名为id的自增列