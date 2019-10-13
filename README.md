## wrapper 插件

包装枚举插件（例如：0或1 包装成男或女）

### 使用

1.引入jar包，实体类加入@Wrapper注解，指定dictData字典（必填），dictType字典类型（必填），name属性自定义名称（可选）

```
/**
    * 性别 0 男 1女 数组方式
  */
 @Wrapper(dictData = {"0:男","1:女"},dictType = "array",name = "demo")
 private int sex;
```

```
/**
    * 性别 0 男 1女 配置方式: 如果是配置方式，config.properties在配置 0=男 1=女
  */
  @Wrapper(dictData = {"0","1"},dictType = "config", name = "demo")
  private int sex;
```

2.在需要数据转换地方调用方法

```
 @GetMapping("/")
       public List<Map<String, Object>> hello() {
          // 初始化数据
           List<Student> students = new ArrayList<>();
           for (int i = 0; i <= 10; i++ ) {
               Student student = new Student(i, "张三" + i , i % 2);
               students.add(student);
           }
            // 调用
           return ControllerWrapper.getInstance().wrap(students)(students);
       }
```
3.效果展示

```
[
 {
 name: "张三0",
 id: "0",
 demo: "男"
 },
 {
 name: "张三1",
 id: "1",
 demo: "女"
 },
 {
 name: "张三2",
 id: "2",
 demo: "男"
 },
 {
 name: "张三3",
 id: "3",
 demo: "女"
 }
 ....
 ]
```

