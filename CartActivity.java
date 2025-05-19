package com.example.clitz.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clitz.Adapter.CartAdapter;
import com.example.clitz.Helper.ChangeNumberItemsListener;
import com.example.clitz.Helper.ManagementCart;
import com.example.clitz.R;
import com.example.clitz.databinding.ActivityCartBinding;

public class CartActivity extends BaseActivity {
    private ActivityCartBinding binding;
    private RecyclerView.Adapter adapter;
    private ManagementCart managementCart;
    private double tax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managementCart=new ManagementCart(this);

        setVariable();
        calculateCart();
        initList();
    }

    private void initList() {
        if(managementCart.getListCart().isEmpty()){
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.scrollviewCart.setVisibility(View.GONE);

        }else {
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scrollviewCart.setVisibility(View.VISIBLE);
        }

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        binding.cardView.setLayoutManager(linearLayoutManager);
        adapter=new CartAdapter(managementCart.getListCart(), this, () -> calculateCart());
        binding.cardView.setAdapter(adapter);

    }

    private void calculateCart() {
        double percentTax=0.02; //percent 2% tax
        double delivery=10; // 10 Rupees

        tax=Math.round(managementCart.getTotalFee()*percentTax*100.0)/100;
        double total=Math.round((managementCart.getTotalFee()+tax+delivery)*100)/100;
        double itemTotal=Math.round(managementCart.getTotalFee()*100)/100;

        binding.totalfeeTxt.setText("₹"+itemTotal);
        binding.taxTxt.setText("₹"+tax);
        binding.deliveryTxt.setText("₹"+delivery);
        binding.totalTxt.setText("₹"+total);

    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());
    }
}