package com.example.tp5_df

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.tp5_df.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener,SensorEventListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var sensorMgr: SensorManager
    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener(this)

        sensorMgr=getSystemService(SENSOR_SERVICE) as SensorManager
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.nav_accelerometer ->{
                var b : Boolean = false
                val monAccelo = sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
                if (monAccelo!= null)
                b=sensorMgr.registerListener(this,monAccelo,SensorManager.SENSOR_DELAY_NORMAL)
                else Toast.makeText(this, "introuvable", Toast.LENGTH_LONG).show()

                if (b) Toast.makeText(this, "Activé", Toast.LENGTH_LONG).show()
                else Toast.makeText(this, "PBM", Toast.LENGTH_LONG).show()
                true
            }
            R.id.nav_gyroscopre ->{
                var b : Boolean = false
                val monAccelo = sensorMgr.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
                if (monAccelo!= null)
                    b=sensorMgr.registerListener(this,monAccelo,SensorManager.SENSOR_DELAY_NORMAL)
                else Toast.makeText(this, "introuvable", Toast.LENGTH_LONG).show()

                if (b) Toast.makeText(this, "Activé", Toast.LENGTH_LONG).show()
                else Toast.makeText(this, "PBM", Toast.LENGTH_LONG).show()
                true
            }
            R.id.nav_magnetometer ->{
                var b : Boolean = false
                val monAccelo = sensorMgr.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
                if (monAccelo!= null)
                    b=sensorMgr.registerListener(this,monAccelo,SensorManager.SENSOR_DELAY_NORMAL)
                else Toast.makeText(this, "introuvable", Toast.LENGTH_LONG).show()

                if (b) Toast.makeText(this, "Activé", Toast.LENGTH_LONG).show()
                else Toast.makeText(this, "PBM", Toast.LENGTH_LONG).show()
                true
            }
            R.id.nav_proximity ->{
                var b : Boolean = false
                val monAccelo = sensorMgr.getDefaultSensor(Sensor.TYPE_PROXIMITY)
                if (monAccelo!= null)
                    b=sensorMgr.registerListener(this,monAccelo,SensorManager.SENSOR_DELAY_NORMAL)
                else Toast.makeText(this, "introuvable", Toast.LENGTH_LONG).show()

                if (b) Toast.makeText(this, "Activé", Toast.LENGTH_LONG).show()
                else Toast.makeText(this, "PBM", Toast.LENGTH_LONG).show()
                true
            }
            R.id.nav_photometer ->{
                var b : Boolean = false
                val monAccelo = sensorMgr.getDefaultSensor(Sensor.TYPE_LIGHT)
                if (monAccelo!= null)
                    b=sensorMgr.registerListener(this,monAccelo,SensorManager.SENSOR_DELAY_NORMAL)
                else Toast.makeText(this, "introuvable", Toast.LENGTH_LONG).show()

                if (b) Toast.makeText(this, "Activé", Toast.LENGTH_LONG).show()
                else Toast.makeText(this, "PBM", Toast.LENGTH_LONG).show()
                true
            }

            else -> {false}
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
    if(event != null){
        when(event.sensor.type){
            Sensor.TYPE_ACCELEROMETER -> {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]
                Log.i("Acc:", "$x, $y, $z")
            }
            Sensor.TYPE_PROXIMITY -> {
                val x = event.values[0]

                Log.i("Prox:", "$x")
            }
            Sensor.TYPE_LIGHT -> {
                val x = event.values[0]

                Log.i("Light:", "$x")
            }
        }
    }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }
}