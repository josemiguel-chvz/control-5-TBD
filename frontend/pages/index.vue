<template>
  <div>
    <b-container fluid>
      <div class="outer mt-2">
          <div class="middle">
              <div class="inner">
                    <b-card
                      border-variant="primary"
                      header="Seleccionar Institución"
                      header-bg-variant="primary"
                      header-text-variant="white"
                      align="center"
                  >
                    <b-button variant="primary" size="lg" @click="new_institution()">Crear Institución</b-button>
                    <b-card-text>
                      <div class="mt-3">
                          <p>También puedes seleccionar uno de la lista</p>
                      </div>
                      <div>
                        <b-table head-variant="dark" bordered hover :items="institutions" :fields="fields">
                          <template v-slot:cell(settings)="institution">
                          <b-button size="sm"  variant="primary" @click="view_institution(institution.item.id)">Seleccionar</b-button>
                          <b-button size="sm"  variant="danger" @click="open_delete_modal(institution.item)">Eliminar</b-button>
                          </template>
                        </b-table>    
                      </div>
                    </b-card-text>
                  </b-card>
              </div>
          </div>
      </div>
      <div>
        <b-modal centered title="Eliminar institución" v-model="show_delete_modal">
            <p class="my-4">¿Esta seguro que desea eliminar la institución seleccionada?: {{selected_institution.name}}?</p>
            <template #modal-footer>
            <div class="w-100">
                <b-row  class="float-right">
                    <b-col>
                        <b-button
                            variant="danger"
                            size="sm"
                            @click="delete_institution(selected_institution.id)"
                        >
                        Eliminar
                        </b-button>
                    </b-col>
                    <b-col>
                        <b-button
                            variant="primary"
                            size="sm"
                            @click="show_delete_modal=false"
                            >
                            Cancelar
                        </b-button>
                    </b-col>
                </b-row>
            </div>
            </template>
        </b-modal>   
      </div>
    </b-container>
  </div>
</template>

<script>
export default {
    data(){
        return {
        show_delete_modal: false,
        selected_institution: {},
        institutions: [],
        fields: [
          {
            key: 'id',
            label: '#'
          },
          {
            key: 'name',
            label: 'Nombre'
          },
          {
            key: 'email',
            label: 'Correo'
          },
          {
            key: 'contact',
            label: 'Numero de contacto'
          },
          {
            key: 'settings',
            label: 'Configuración'
          }
        ]
    }
  },
  created() {
    this.getData();
  },
  methods: {
    getData: async function() {
      try {
        let response = await this.$axios.get('institutions/all');
        this.institutions = response.data;
      } catch (error) {
        console.log(error)
      }
    },
    new_institution: function() {
      this.$router.push({path: `/new`});
    },
    view_institution: function(event) {
      let institution_id = event;
      this.$router.push({path: institution_id});
    },
    open_delete_modal: function(event) {
      this.show_delete_modal = true;
      this.selected_institution = event;
    },
    delete_institution: async function(institution_id) {
       try {
        let response = await this.$axios.delete(institution_id);
        if (response.status == 204) {
         location.reload();
        }
      } catch (error) {
        console.log(error)
      }
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