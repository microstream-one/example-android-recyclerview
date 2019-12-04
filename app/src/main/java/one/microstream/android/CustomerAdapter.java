package one.microstream.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import one.microstream.android.data.Customer;
import one.microstream.android.data.CustomerRepository;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {

    private CustomerRepository customerRepository;

    CustomerAdapter(Context context) {
        customerRepository = new CustomerRepository(context);
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.customer_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutId, parent, false);
        return new CustomerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        Customer customer = customerRepository.findCustomerById(position);
        holder.showCustomer(customer);
    }


    @Override
    public void onViewRecycled(@NonNull CustomerViewHolder holder) {
        super.onViewRecycled(holder);
        Customer customer = holder.getCustomer();
        customerRepository.clearLazyRefence(customer.getId());
    }

    @Override
    public int getItemCount() {
        return 15000;
    }

    void shutdownStorage() {
        customerRepository.shutdownStorage();
    }


    class CustomerViewHolder extends RecyclerView.ViewHolder {

        TextView customerFirstName;
        TextView customerLastName;
        TextView customerId;
        TextView customerAddress;
        Customer customer;

        CustomerViewHolder(View view) {
            super(view);
            customerFirstName = view.findViewById(R.id.customerName);
            customerLastName = view.findViewById(R.id.customerLastName);
            customerAddress = view.findViewById(R.id.CustomerAddress);
            customerId = view.findViewById(R.id.customerId);
        }

        void showCustomer(Customer customer) {
            this.customer = customer;
            customerFirstName.setText(customer.getFirstName());
            customerLastName.setText(customer.getLastName());
            customerId.setText(String.valueOf(customer.getId()));
            customerAddress.setText(String.format("%s %s %s %s ", customer.getStreet(), customer.getStreetNumber(), customer.getCity(), customer.getZip()));
        }

        Customer getCustomer() {
            return customer;
        }
    }
}
