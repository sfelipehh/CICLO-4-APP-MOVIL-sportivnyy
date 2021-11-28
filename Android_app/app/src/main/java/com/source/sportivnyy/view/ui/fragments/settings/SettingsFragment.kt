package com.source.sportivnyy.view.ui.fragments.settings

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.Preference.OnPreferenceChangeListener
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import com.source.sportivnyy.R
class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        setHasOptionsMenu(true)
        val metodoDePagoPreference=preferenceScreen.findPreference<EditTextPreference>("metodo_pago")
        var metodoDePagoText = metodoDePagoPreference!!.text
        metodoDePagoPreference.setOnPreferenceChangeListener { _, _ ->
            metodoDePagoCambio(metodoDePagoPreference, metodoDePagoText)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val menuItems = setOf(R.id.to_shopping_cart,R.id.to_account)
        for (item in menuItems){
            menu.findItem(item).isVisible=false
        }
    }
    fun metodoDePagoCambio(preference: Preference,nuevo_valor:Any):Boolean{
        val texto:String = nuevo_valor.toString()
        Toast.makeText(
            preference.context,
            "Su metodo de pago ha cambiado",
            Toast.LENGTH_LONG
        ).show()
        return true
    }
}