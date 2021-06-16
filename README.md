# LastFM-Data

An Android library for communication with an external service. The external service is

![alt tag](images/lastfm.png)

----

# Getting started

To get started using this library, follow the steps below.

1- Add the library into the **libs** folder

2- To indicate the new module´s path, in **settings.gradle** add 

```kotlin
include ':lastfmdata'    
project(':lastfmdata').projectDir = new File('libs/LastFMData')
```

3- Sync Gradle with **Sync now**

4- In **builde.gradle** add
```kotlin
implementation project("lastfmdata")
```

5- Sync Gradle with **Sync now**

----

# LastFM Setup

Instantiate an instance of LastFM-Data
```kotlin
val lastFMInfoService: LastFMInfoService
```

You can retrieve the information using
```kotlin
lastFMInfoService.getCardInfo(artistName)
```
