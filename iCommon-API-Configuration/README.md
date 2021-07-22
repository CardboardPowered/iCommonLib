# Configuration API [![Public Domain](https://img.shields.io/badge/license-The%20Unlicense-blue)](https://unlicense.org/)

A two file tiny YAML-like configuration parser for Java.
Provides support for basic YAML configuration.

Example:
```
# Example Config File
thekey:
    toget: Hello!
mynumber: 10
enable: true
```

```java
FileConfiguration config = new FileConfiguration( ... );
String stuff = config.getString("thekey.toget");
int value = config.getInt("mynumber");
// String, int, double, boolean, etc..

config.set("enable", "false");
config.save();
```

![PD](https://upload.wikimedia.org/wikipedia/commons/8/84/Public_Domain_Mark_button.svg)