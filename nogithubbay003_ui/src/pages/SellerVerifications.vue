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
            <sellerVerification-table
            v-if="sellerVerifications && sellerVerifications.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:sellerVerifications="sellerVerifications"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-seller-verifications="getAllSellerVerifications"
             >

            </sellerVerification-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import SellerVerificationTable from "@/components/SellerVerificationTable";
import SellerVerificationService from "../services/SellerVerificationService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    SellerVerificationTable,
  },
  data() {
    return {
      sellerVerifications: [],
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
    async getAllSellerVerifications(sortBy='sellerVerificationId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await SellerVerificationService.getAllSellerVerifications(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.sellerVerifications.length) {
					this.sellerVerifications = response.data.sellerVerifications;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching sellerVerifications:", error);
        }
        
      } catch (error) {
        console.error("Error fetching sellerVerification details:", error);
      }
    },
  },
  mounted() {
    this.getAllSellerVerifications();
  },
  created() {
    this.$root.$on('searchQueryForSellerVerificationsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllSellerVerifications();
    })
  }
};
</script>
<style></style>
