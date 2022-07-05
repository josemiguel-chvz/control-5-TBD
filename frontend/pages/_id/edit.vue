<template>
  <b-container class="mt-4">
    <b-card no-body>
      <b-row no-gutters>
        <b-col md="7">
          <b-card-body title="Editar Institución">
            <b-card-text>
              <form>
                <b-row>
                  <b-col>
                    <b-form-group
                      id="fname"
                      description="Nombre de la institución"
                      label="Ingrese el nombre:"
                      label-for="name"
                      valid-feedback="Gracias!"
                    >
                    <b-form-input id="name" v-model="institution.name" trim></b-form-input>
                    </b-form-group>
                  </b-col>
                  <b-col>
                    <b-form-group
                      id="email"
                      description="Correo electrónico"
                      label="Ingrese correo:"
                      label-for="email"
                      valid-feedback="Gracias!"
                    >
                      <b-form-input id="email" v-model="institution.email" trim></b-form-input>
                    </b-form-group>
                  </b-col>
                  <b-col>
                    <b-form-group
                      id="contacto"
                      description="Numero de contacto"
                      label="Ingrese telefono:"
                      label-for="contacto"
                      valid-feedback="Gracias!"
                    >
                      <b-form-input  id="contacto" v-model="institution.contact"  trim></b-form-input>
                    </b-form-group>
                  </b-col>
                </b-row>
                <div class="mt-2">
                  <b-row>
                    <b-col cols="12" class="pb-2">
                      <b-button type="button" size="md" @click="update" variant="outline-success">
                        Actualizar
                      </b-button>
                    </b-col>
                    <b-col cols="12" class="mt-1">
                      <b-alert show variant="success" fade v-model="show_success_alert">Institución actualizada correctamente</b-alert>
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
        return (this.institution.name.length >= 1 && this.institution.name.length < 200)
      },
      state2() {
        return (this.institution.email.length >= 1 && this.institution.email.length < 200)
      },
      state3() {
        return (this.institution.contact.length >= 8)
      },

      invalidFeedback1() {
        if (this.institution.name.length > 0) {
          return 'Ingrese al menos 1 caracteres y maximo 200'
        }
        return 'Por favor, rellene el campo'
      },
      invalidFeedback2() {
        if (this.institution.email.length > 0) {
          return 'Ingrese al menos 1 caracteres y maximo 200'
        }
        return 'Por favor, rellene el campo'
      },
      invalidFeedback3() {
        if (this.institution.contact.length > 0) {
          return 'Ingrese al menos 8 caracteres'
        }
        return 'Por favor, rellene el campo'
      }

    },
    data(){
      return {
          show_success_alert: false,
          show_error_alert: false,
          institution:{}
        }
    },
    created() {
      this.getData();
    },
    methods:{
      sleep: function(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
      },
      getData: async function() {
        try {
          let institution_id = this.$route.params.id;
          let response = await this.$axios.get("/institutions/"+institution_id);
          this.institution = response.data;
        } catch (error) {
          console.log(error)
        }
      },
      update:async function(){
        try {
          let institution_id = this.$route.params.id
          let response = await this.$axios.put("/institutions/"+institution_id, this.institution);
          if (response.status == 200) {
            this.show_error_alert = false;
            this.show_success_alert = true;
            await this.sleep(1500);
            let institution_id = response.data.id;
            this.$router.push({path: `/${institution_id}`});
          }
        }catch (error) {
          console.log("error", error)
        }
      },
    }
}
</script>