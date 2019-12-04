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
