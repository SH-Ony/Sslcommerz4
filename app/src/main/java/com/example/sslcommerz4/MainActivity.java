package com.example.sslcommerz4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.sslwireless.sslcommerzlibrary.model.initializer.CustomerInfoInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization;
import com.sslwireless.sslcommerzlibrary.model.initializer.ShipmentInfoInitializer;
import com.sslwireless.sslcommerzlibrary.model.response.TransactionInfoModel;
import com.sslwireless.sslcommerzlibrary.model.util.CurrencyType;
import com.sslwireless.sslcommerzlibrary.model.util.SdkType;
import com.sslwireless.sslcommerzlibrary.view.singleton.IntegrateSSLCommerz;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.TransactionResponseListener;

public class MainActivity extends AppCompatActivity implements TransactionResponseListener {

    TextView success,fail,cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        success=findViewById(R.id.success);
        fail= findViewById(R.id.fail);
        cancel=findViewById(R.id.cancel);

        final SSLCommerzInitialization sslCommerzInitialization = new SSLCommerzInitialization ("onyon625509a977724","onyon625509a977724@ssl", 50, CurrencyType.BDT,"123456789098765", "yourProductType", SdkType.TESTBOX);

        final CustomerInfoInitializer customerInfoInitializer = new CustomerInfoInitializer("customer name", "customer email",
                "address", "dhaka", "1214", "Bangladesh", "phoneNumber");
        final ShipmentInfoInitializer shipmentInfoInitializer = new ShipmentInfoInitializer ("Courier",
                2, new ShipmentInfoInitializer.ShipmentDetails("AA","Address 1",
                "Dhaka","1000","BD"));



        IntegrateSSLCommerz.getInstance(MainActivity.this)
                .addSSLCommerzInitialization(sslCommerzInitialization)
                .buildApiCall(MainActivity.this);


    }

    @Override
    public void transactionSuccess(TransactionInfoModel transactionInfoModel) {


        success.setText(transactionInfoModel.getAPIConnect()+"-----"+transactionInfoModel.getStatus()+"----Paid Amount="+transactionInfoModel.getAmount()+"TK");

    }

    @Override
    public void transactionFail(String s) {
        fail.setText(s);
    }

    @Override
    public void merchantValidationError(String s) {
        cancel.setText(s);

    }


}