# MLog

This is a log tool for Android 
##Features
* Can set Log level
* Can open/close the log by using the method: setEnable(boolean flg)
* Can print log with no tag
* Can set the global Tag name

##Sample Usage
```
MLog.d();//no tag
MLog.d("tag","test");//use the given tag
MLog.d(this,"test");//use the class name as the tag name
```

##License

```

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```