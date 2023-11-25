package com.example.healdon;

public class Doc_register_info {
    public String name, password, email, d_id,clinic_day,address,registraion_no,verified="0";

    public Doc_register_info(String name, String password, String email, String d_id, String clinic_day, String address, String registraion_no) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.d_id = d_id;
        this.clinic_day = clinic_day;
        this.address = address;
        this.registraion_no = registraion_no;
    }
}
