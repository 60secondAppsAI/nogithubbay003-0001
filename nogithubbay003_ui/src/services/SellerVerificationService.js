import http from "../http-common"; 

class SellerVerificationService {
  getAllSellerVerifications(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/sellerVerification/sellerVerifications`, searchDTO);
  }

  get(sellerVerificationId) {
    return this.getRequest(`/sellerVerification/${sellerVerificationId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/sellerVerification?field=${matchData}`, null);
  }

  addSellerVerification(data) {
    return http.post("/sellerVerification/addSellerVerification", data);
  }

  update(data) {
  	return http.post("/sellerVerification/updateSellerVerification", data);
  }
  
  uploadImage(data,sellerVerificationId) {
  	return http.postForm("/sellerVerification/uploadImage/"+sellerVerificationId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new SellerVerificationService();
