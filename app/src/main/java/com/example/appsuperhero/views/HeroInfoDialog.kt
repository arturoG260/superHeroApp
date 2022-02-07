package com.example.appsuperhero.views

import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.appsuperhero.databinding.HeroInfoDialogBinding
import com.example.appsuperhero.models.HeroModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.URL

class HeroInfoDialog(private val heroModel: HeroModel) : DialogFragment() {
    private lateinit var binding: HeroInfoDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HeroInfoDialogBinding.inflate(inflater, container, false)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val widthPixels = (resources.displayMetrics.widthPixels * 0.95).toInt()
        val heightPixels = (resources.displayMetrics.heightPixels * 0.9).toInt()
        dialog?.window?.setLayout(widthPixels, heightPixels)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvHero.text = heroModel.name
        binding.tvInteligenciaValue.text = heroModel.powerstats.intelligence
        binding.tvFuerzaValue.text = heroModel.powerstats.strength
        binding.tvVelocidadValue.text = heroModel.powerstats.speed
        binding.tvDurabilidadValue.text = heroModel.powerstats.durability
        binding.tvPoderValue.text = heroModel.powerstats.power
        binding.tvCombateValue.text = heroModel.powerstats.combat
        binding.tvNombreCompletoValue.text = heroModel.biography.full_name
        binding.tvLugardeNacimientoValue.text = heroModel.biography.place_of_birth
        binding.tvPrimeraAparicionValue.text = heroModel.biography.first_appearance
        binding.tvPublicadoEnValue.text = heroModel.biography.publisher
        binding.tvEstadoValue.text = heroModel.biography.alignment
        binding.tvGeneroValue.text = heroModel.appearance.gender
        binding.tvAlturaValue.text = heroModel.appearance.height[1]
        binding.tvPesoValue.text = heroModel.appearance.weight[1]
        binding.tvColorDeOjosValue.text = heroModel.appearance.eye_color
        binding.tvColorDeCabelloValue.text = heroModel.appearance.hair_color
        binding.tvOcupacionValue.text = heroModel.work.occupation
        binding.tvDescripcionValue.text = heroModel.work.base
        binding.tvGrupodeafiliacionValue.text = heroModel.connections.group_affiliation
        binding.tvRelativosValue.text = heroModel.connections.relatives
        binding.rvAlias.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = AdapterAlias(heroModel.biography.aliases)
        }
        Glide.with(requireActivity())
            .load(heroModel.image.url)
            .into(binding.ivHero)
        CoroutineScope(Dispatchers.IO).launch {
            if (!heroModel.image.url.equals(null)) {
                try {
                    val url = URL(heroModel.image.url)
                    val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                    Palette.Builder(bitmap).generate { palette ->
                        run {
                            if (palette!!.vibrantSwatch?.rgb != null && palette.lightVibrantSwatch?.rgb != null) {
                                binding.clHeader.setBackgroundColor(palette.vibrantSwatch!!.rgb)
                                binding.clContenedor.setBackgroundColor(palette.lightVibrantSwatch!!.rgb)
                            }
                        }
                    }
                } catch (e: IOException) {
                    println(e)
                }
            }
        }
    }
}