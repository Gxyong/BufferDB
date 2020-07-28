## BufferDB 数据库，存放不规则数据，文件数据缓存

gradle配置
```groovy
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

```groovy

  implementation 'io.reactivex.rxjava2:rxjava:2.2.7'
  implementation 'io.reactivex.rxjava2:rxandroid:2.1.1'
  implementation 'com.github.Gxyong:BufferDB:v1.0'
```
AndroidManifest.xml 配置meta-data,value的值是你实现BufferDBModule的类全路径
```groovy
    	<meta-data
            android:name="com.bufferdb.bufferDBModule"
            android:value="xxx.DemoBufferDBModule" />

```
需要权限：
```groovy
	<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
	<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
```  
Android 10 读取文件需要在application 添加一行代码
```groovy

       android:requestLegacyExternalStorage="true"
 
```
详细代码请看[demo](https://github.com/Gxyong/BufferDB/tree/master/app/src/main/java/com/buffer)
