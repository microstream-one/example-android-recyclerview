# Example: Android Recyclerview with Microstream

This example shows how to effectively use Microstream storage with Android RecyclerView. RecyclerView is an effective way to implement a dynamic list of views. 
It is often used by different socials networks to show comments or posts. 
https://developer.android.com/guide/topics/ui/layout/recyclerview

First, you must add the Microstream repository into your project. So in build.gradle file for the whole project, add these lines:
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
and for the application, add the following dependencies:
```gradle
implementation 'one.microstream:storage.embedded:02.02.00-MS-GA'
implementation 'one.microstream:storage.embedded.configuration:02.02.00-MS-GA'
implementation 'androidx.recyclerview:recyclerview:1.1.0'
annotationProcessor "one.microstream:base:02.02.00-MS-GA"
```

This example shows a list of fake customers with name and address. 
The customers are created with JavaFaker library and are stored into a Microstream storage. 
When the view is scrolled, new entries are dynamically created either from existing customer instances loaded from storage or from 
newly created ones that are stored on the fly. This example shows Microstream lazy loading functionality.

```java
package one.microstream.android.data;

import java.util.HashMap;
import java.util.Map;

import one.microstream.reference.Lazy;

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
So, the Application does not need to load all customers from the data store at once, but just the Lazy reference pointing to them. Only when the RecyclerView actually requires customer instances to show them on the display, will they be loaded from the data store if they are not loaded, yet.
RecyclerView also offers the possibility to remove elements from the memory that are not displayed anymore. 
The app just needs to call the clear() method on the Lazy reference.
This approach allows to show thousands of items on the screen without ruining the app's heap.

This project is ready to be built with Android studio.

