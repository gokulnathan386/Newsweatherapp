package com.example.newsweatherapp.Activity



import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.newsweatherapp.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.io.IOException

import android.provider.Settings
import android.util.Log
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private  val LOCATION_PERMISSION_REQUEST_CODE = 123


    override fun onCreate(savedInstanceState: Bundle?) {
        val actionBar: ActionBar? = supportActionBar
        actionBar!!.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val phase1 = findViewById<Button>(R.id.Phase1)
        val phase2 = findViewById<Button>(R.id.Phase2)

        phase1.setOnClickListener{
            val k = Intent(this,NewsScreenActivity::class.java)
            startActivity(k)
        }

        phase2.setOnClickListener{
            locationPermission()
        }




    }

    private fun locationPermission() {


        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let {
                        getAddressFromLocation(it, this)
                    } ?: run {
                        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager?
                        if (locationManager != null) {
                            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                                startActivity(intent)
                            }
                        }else{
                            Toast.makeText(this, "Location not available", Toast.LENGTH_SHORT).show()
                        }


                    }
                }
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        }

    }


    private fun getAddressFromLocation(location: Location, context: Context) {
        val geocoder = Geocoder(context, Locale.getDefault())
        try {
            val addresses: List<Address> = geocoder.getFromLocation(
                location.latitude,
                location.longitude,
                1
            ) as List<Address>

            if (addresses.isNotEmpty()) {
                  val address = addresses[0]
                  val city = address.locality
                  val state = address.adminArea
                  val country = address.countryName

                  val fullAddress = address.getAddressLine(0)
                  val k = Intent(this,WeatherScreenActivity::class.java)
                  k.putExtra("City", city)
                  k.putExtra("State", state)
                  k.putExtra("country", country)
                  k.putExtra("longitude", location.longitude)
                  k.putExtra("latitude", location.latitude)
                  startActivity(k)
                 Log.d("dcbjhdbchjbdc","" +location.longitude +"fd"+location.latitude)

            } else {
                Toast.makeText(this, "Address not found", Toast.LENGTH_SHORT).show()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Error getting address", Toast.LENGTH_SHORT).show()
        }
    }

}