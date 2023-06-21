package com.fastporte.controller.fragments.Faq

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.R
import com.fastporte.models.Faq


class FaqFragment : Fragment() {

    lateinit var faqList: List<Faq>
    lateinit var faqRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_faq, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        faqRecyclerView = view.findViewById(R.id.rvFaq)
        loadData(view)
    }


    private fun loadData(view: View) {
        //Agregar un elemento a la lista faqList
        faqList = listOf(
            Faq(
                "¿Cómo solicito un servicio de transporte en la aplicación?",
                "Para solicitar un servicio de transporte, abre la aplicación y busca conductores disponibles en tu área. Puedes explorar los perfiles de los conductores y seleccionar uno en específico. Luego, visita su perfil y utiliza la opción de solicitud de servicio para indicar la ubicación, destino y hora del transporte."
            ),

            Faq(
                "¿Cómo puedo pagar por el servicio de transporte?",
                "Puedes pagar por el servicio de transporte utilizando tarjetas de crédito. En la sección de perfil de tu cuenta, puedes agregar y guardar tus tarjetas de crédito para realizar pagos rápidos y seguros. El pago se realizará al conductor una vez que este acepte la solicitud de servicio."
            ),

            Faq(
                "¿Qué sucede si un conductor rechaza mi solicitud de transporte?",
                "Si un conductor rechaza tu solicitud de transporte, la aplicación te notificará y te ofrecerá la opción de buscar otros conductores disponibles. Podrás seleccionar un nuevo conductor y enviarles una solicitud de servicio."
            ),

            Faq(
                "¿Cómo puedo rastrear el progreso de mi transporte?",
                "Después de que un conductor acepte tu solicitud de servicio, podrás rastrear su ubicación y el progreso del transporte en tiempo real a través de la función de geolocalización de la aplicación. Esto te permitirá saber dónde se encuentra el conductor y cuánto falta para llegar a tu destino."
            ),

            Faq(
                "¿Qué sucede si un conductor no cumple con el servicio solicitado?",
                "Si un conductor no cumple con el servicio solicitado, puedes comunicarte directamente con ellos a través del número de teléfono proporcionado en su perfil para resolver el problema. Si no se encuentra una solución satisfactoria, puedes contactar al servicio de atención al cliente de la aplicación para informar sobre la situación y solicitar un reembolso."
            ),

            Faq(
                "¿Puedo dejar comentarios sobre el conductor después de completar el servicio?",
                "Sí, una vez que se complete el servicio, tendrás la opción de dejar comentarios y calificaciones sobre el conductor en la aplicación. Esto permite a otros usuarios conocer la calidad del servicio proporcionado y ayuda a mantener altos estándares de rendimiento para los conductores."
            ),

            Faq(
                "¿Puedo programar un servicio de transporte para una fecha y hora específicas?",
                "Sí, puedes programar un servicio de transporte para una fecha y hora específicas. Al visitar el perfil de un conductor, deberás encontrar la opción de programación de servicio donde podrás seleccionar la fecha, hora y ubicación deseada para el transporte."
            ),

            Faq(
                "¿Puedo cancelar mi solicitud de transporte después de haberla confirmado?",
                "Sí, puedes cancelar tu solicitud de transporte después de haberla confirmado. Sin embargo, es posible que se aplique una tarifa de cancelación dependiendo de la política de la aplicación. La tarifa de cancelación se te informará antes de confirmar la cancelación."
            ),

            Faq(
                "¿Cómo puedo contactar directamente al conductor?",
                "Puedes contactar directamente al conductor a través del número de teléfono proporcionado en su perfil. Al visitar el perfil del conductor seleccionado, encontrarás su información de contacto, lo que te permitirá comunicarte con ellos para coordinar detalles adicionales del transporte."
            ),

            Faq(
                "¿Qué medidas de seguridad se toman para proteger mi información personal y financiera?",
                "La aplicación implementa medidas de seguridad robustas para proteger tu información personal y financiera. Se utilizan tecnologías de encriptación y se siguen las mejores prácticas de seguridad en la industria para garantizar la confidencialidad y protección de tus datos. Además, se recomienda no compartir información personal sensible a través de la plataforma de mensajería de la aplicación."
            )

        )

        faqRecyclerView.layoutManager = LinearLayoutManager(view.context)
        faqRecyclerView.adapter = FaqAdapter(faqList, view.context)
    }

}