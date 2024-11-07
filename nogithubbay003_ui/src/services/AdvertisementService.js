import http from "../http-common"; 

class AdvertisementService {
  getAllAdvertisements(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/advertisement/advertisements`, searchDTO);
  }

  get(advertisementId) {
    return this.getRequest(`/advertisement/${advertisementId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/advertisement?field=${matchData}`, null);
  }

  addAdvertisement(data) {
    return http.post("/advertisement/addAdvertisement", data);
  }

  update(data) {
  	return http.post("/advertisement/updateAdvertisement", data);
  }
  
  uploadImage(data,advertisementId) {
  	return http.postForm("/advertisement/uploadImage/"+advertisementId, data);
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

export default new AdvertisementService();
