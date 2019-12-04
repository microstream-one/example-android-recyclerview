# Example: Android Recyclerview with Microstream

This example shows how to effective use Microstream storage with Android Recyclerview. Recyclerview is an effective way how to implement list of views. 
It is often used by different socials networks to shows the comments or posts. 
https://developer.android.com/guide/topics/ui/layout/recyclerview

First you must add Microstream repository into your project. So in build.gralde file for whole project add these lines:
```gradle
allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url 'https://repo.microstream.one/repository/maven-public/'
        }
    }
}
```
and for application add following dependencies:
```gradle
implementation 'one.microstream:storage.embedded:02.01.00-MS-GA'
implementation 'one.microstream:storage.embedded.configuration:02.01.00-MS-GA'
implementation 'androidx.recyclerview:recyclerview:1.1.0'
```

Older version of Microstream did not support Android. 
This example shows the list of fake Customer, with name and address. 
The Customers are created with Javafaker library and stored into Microstream storage. 
When the view is scrolled, the new customer is loaded from storage and when not exists, 
one new is generated and stored. This example shows Microstream lazy functionality.

```java
package one.microstream.android.data;

import java.util.HashMap;
import java.util.Map;

import one.microstream.persistence.lazy.Lazy;

public class CustomerRoot {

    private Map<Integer, Lazy<Customer>> customerMap;

    public CustomerRoot() {
        customerMap = new HashMap<>();
    }

    public Map<Integer, Lazy<Customer>> getCustomerMap() {
        return customerMap;
    }
}
```
So, the Application does not need to load from the Datastore all Customers at the same time, 
we load just Lazy reference and when the app is created and the Recyclerview will ask the database only for these customers 
which are shown on Display. 
Recyclerview offers also the possibility to remove elements what has already been seen from the memory. 
The app needs just call clear() method on the Lazy reference. 
This approach allows you to show thousands of items on the screen, without to ruin your heap.

This project is ready to build with Android studio. 

