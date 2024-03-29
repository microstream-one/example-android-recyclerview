package one.microstream.android.data;

import android.content.Context;

import com.github.javafaker.Faker;

import java.nio.file.Path;

import one.microstream.reference.Lazy;
import one.microstream.storage.embedded.types.EmbeddedStorage;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;

public class CustomerRepository {

    private EmbeddedStorageManager storage;
    private CustomerRoot customerRoot = new CustomerRoot();
    private Faker faker;

    public CustomerRepository(Context context) {
        Path filesDir = context.getFilesDir().toPath();
        storage = EmbeddedStorage.start(customerRoot, filesDir);
        faker = new Faker();
    }

    public void clearLazyRefence(Integer customerId) {
        Lazy<Customer> lazy = customerRoot.getCustomerMap().get(customerId);
        if (lazy != null) {
            lazy.clear();
        }

    }

    public Customer findCustomerById(Integer id) {
        Customer customer;
        Lazy<Customer> lazyCustomer = customerRoot.getCustomerMap().get(id);
        if (lazyCustomer != null) {
            customer = lazyCustomer.get();
        } else {
            customer = new Customer();
            customer.setFirstName(faker.name().firstName());
            customer.setLastName(faker.name().lastName());
            customer.setCity(faker.address().city());
            customer.setStreet(faker.address().streetName());
            customer.setStreetNumber(faker.address().streetAddressNumber());
            customer.setZip(faker.address().zipCode());
            customer.setId(id);
            customerRoot.getCustomerMap().put(id, Lazy.Reference(customer));
            storage.store(customerRoot.getCustomerMap());
        }
        return customer;
    }

    public void shutdownStorage() {
        storage.shutdown();
    }
}
