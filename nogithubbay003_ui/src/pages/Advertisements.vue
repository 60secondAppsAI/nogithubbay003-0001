<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <advertisement-table
            v-if="advertisements && advertisements.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:advertisements="advertisements"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-advertisements="getAllAdvertisements"
             >

            </advertisement-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import AdvertisementTable from "@/components/AdvertisementTable";
import AdvertisementService from "../services/AdvertisementService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    AdvertisementTable,
  },
  data() {
    return {
      advertisements: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllAdvertisements(sortBy='advertisementId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await AdvertisementService.getAllAdvertisements(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.advertisements.length) {
					this.advertisements = response.data.advertisements;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching advertisements:", error);
        }
        
      } catch (error) {
        console.error("Error fetching advertisement details:", error);
      }
    },
  },
  mounted() {
    this.getAllAdvertisements();
  },
  created() {
    this.$root.$on('searchQueryForAdvertisementsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllAdvertisements();
    })
  }
};
</script>
<style></style>
