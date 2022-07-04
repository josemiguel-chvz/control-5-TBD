<template>
  <b-container class="mt-4">
    <b-card no-body>
      <b-row no-gutters>
        <b-col md="7">
          <b-card-body title="Crear Institución">
            <b-card-text>
              <form>
                <b-row>
                  <b-col>
                    <b-form-group
                      id="name"
                      description="Nombre de la institución"
                      label="Ingrese el nombre:"
                      label-for="name"
                      valid-feedback="Gracias!"
                      :invalid-feedback="invalidFeedback1"
                    >
                    <b-form-input id="name" v-model="new_institution.name" :state="state1" trim></b-form-input>
                    </b-form-group>
                  </b-col>
                  <b-col>
                    <b-form-group
                      id="email"
                      description="Correo electrónico"
                      label="Ingrese correo:"
                      label-for="email"
                      valid-feedback="Gracias!"
                      :invalid-feedback="invalidFeedback2"
                    >
                      <b-form-input id="email" type="email" v-model="new_institution.email" :state="state2" trim></b-form-input>
                    </b-form-group>
                  </b-col>
                   <b-col>
                    <b-form-group
                      id="contacto"
                      description="Numero de contacto"
                      label="Ingrese telefono:"
                      label-for="contacto"
                      valid-feedback="Gracias!"
                      :invalid-feedback="invalidFeedback3"
                    >
                      <b-form-input id="contacto" v-model="new_institution.contact" :state="state3" trim></b-form-input>
                    </b-form-group>
                  </b-col>
                </b-row>
                <div class="mt-2">
                  <b-row>
                    <b-col cols="12" class="pb-2">
                      <b-button type="button" size="md" @click="create" variant="outline-success">
                        Registrar
                      </b-button>
                    </b-col>
                    <b-col cols="12" class="mt-1">
                      <b-alert show variant="success" fade v-model="show_success_alert">Institución creada correctamente</b-alert>
                    </b-col>
                  </b-row>
                </div>
              </form>
            </b-card-text>
          </b-card-body>
        </b-col>
      </b-row>
    </b-card>
  </b-container>
</template>

<script>
  export default {
    computed: {
      state1() {
        return (this.new_institution.name.length >= 1 && this.new_institution.name.length < 200)
      },
      state2() {
        return (this.new_institution.email.length >= 1 && this.new_institution.email.length < 200)
      },
      state3() {
        return (this.new_institution.contact.length >= 8)
      },

      invalidFeedback1() {
        if (this.new_institution.name.length > 0) {
          return 'Ingrese al menos 1 caracteres y maximo 200'
        }
        return 'Por favor, rellene el campo'
      },
      invalidFeedback2() {
        if (this.new_institution.email.length > 0) {
          return 'Ingrese al menos 1 caracteres y maximo 200'
        }
        return 'Por favor, rellene el campo'
      },
      invalidFeedback3() {
        if (this.new_institution.contact.length > 0) {
          return 'Ingrese al menos 8 caracteres'
        }
        return 'Por favor, rellene el campo'
      }

    },
    data(){
      return {
          show_success_alert: false,
          show_error_alert: false,
          new_institution:{
            name: "",
            email:"",
            contact:""
          }
        }
    },
    methods:{
      sleep: function(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
      },
      create:async function(){
        try {
          let response = await this.$axios.post("/api/institutions/",this.new_institution);
          if (response.status == 201) {
            this.show_error_alert = false;
            this.show_success_alert = true;
            await this.sleep(1500);
            let institution_id = response.data.id;
            this.$router.push({path: `/index/${institution_id}`});
          }
        }catch (error) {
          console.log("error", error)
        }         
      },
    }
}
</script>