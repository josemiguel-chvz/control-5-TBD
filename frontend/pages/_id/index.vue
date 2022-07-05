<template>
  <b-container fluid>
    <b-row class="mt-4">
      <b-col>
        <b-card>
          <b-card-title>Institución</b-card-title>
          <b-card-text>
            <b-row>
              <b-col cols=2>
                <b-img  style="width:250px;" src="https://userscontent2.emaze.com/images/9263837b-1949-4bd7-8271-c0e49f68c4ed/11ce17d9d200fc1b86a808e93f1f411d.jpg" fluid thumbnail></b-img>   
              </b-col>
              <b-col cols=6>
                <p>
                  <br>
                  Nombre: {{institution.name}}
                  <br>
                  Correo: {{institution.email}}
                  <br>
                  Contacto: {{institution.contact}}
                </p>
              </b-col>
              <b-col>
                <b-button size="sm" v-b-toggle.sidebar-variant>Ver más</b-button>
                <b-button size="sm"  variant="primary" @click="edit_institution(institution.id)">Editar</b-button>
              </b-col>
            </b-row>
          </b-card-text>
        </b-card>
      </b-col>
    </b-row>
    <b-sidebar id="sidebar-variant" title="Descripción" bg-variant="dark" text-variant="light" shadow>
      <div class="px-3 py-4">
        <p>
        La institucion {{institution.name}} existe bajo el proposito
        de brindar ayuda a la ciudadania en momentos tan dificiles
        como son las catastrofes tanto naturales como producidas
        por el humano. Su correo de contacto es: {{institution.email}}.

        Para mas información, comuniquese a {{institution.contact}}
        </p>
        <b-img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSG-rqc6tZoFtvRaRmNhz1fyWKJ5O88SxJF7A&usqp=CAU" fluid thumbnail></b-img>
      </div>
    </b-sidebar>
  </b-container>
</template>

<script>

export default {
  data () {
    return{
	  show_delete_modal: false,
      institution: {}
    }
  },
  created() {
    this.getData();
  },
  methods: {
    getData: async function() {
      try {
		let institution_id = this.$route.params.id;
        let response = await this.$axios.get("/api/institutions/"+institution_id);
        this.institution = response.data;
      } catch (error) {
        console.log(error)
      }
    },
	  edit_institution: function(event) {
      let institution_id = event;
      this.$router.push({path: institution_id+"/edit"});
    }
  } 
}
</script>

<style>
.outer {
  display: table;
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
}

.middle {
  display: table-cell;
  vertical-align: middle;
}

.inner {
  margin-left: auto;
  margin-right: auto;
  width: 1000px;
}
</style>