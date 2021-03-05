## 对数组的操作

#### Arrays.fill() - 填充数组

```java
void fill(int[] a, int val)  //全部填充 
void fill(int[] a, int fromIndex, int toIndex, int val)  //填充指定索引的元素
```

#### Arrays.sort() - 对数组排序

```j'a
void sort(int[] a)  //全部排序 
void sort(int[] a, int fromIndex, int toIndex)  //排序指定索引的元素
```

#### Arrays.copyOf() - 复制数组

```java
int[] copyOf(int[] original, int newLength)  //复制数组，指定新数组长度 
int[] copyOfRange(int[] original, int from, int to) //复制数组，指定所复制的原数组的索引
```

#### Arrays.asList() - 将Array转换成List&lt;String&gt;

```java
//检查数组中是否包含某一个值
String[] stringArray = { "a", "b", "c", "d", "e" };
boolean b = Arrays.asList(stringArray).contains("a");
```

### ArrayUtils

```java
int[] addAll(int[] Array1,int[] Array2)  // 连接
int[] reverse(int[] Array)  // 翻转
int[] removeElement(int[] Array, int position) // 移除
```

#### 打印数组

```java
Arrays.toString(arr)  
for(int n: arr)   System.out.println(n+", ");  
System.out.println(Arrays.asList(arr));  
Arrays.asList(arr).stream().forEach(s -> System.out.println(s));
```

#### 定位数组元素

```java
Arrays.binarySearch(str);  //有序数组
Arrays.asList.indexOf(str);
```

