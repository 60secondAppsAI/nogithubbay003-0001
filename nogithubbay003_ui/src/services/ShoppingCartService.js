import http from "../http-common"; 

class ShoppingCartService {
  getAllShoppingCarts(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/shoppingCart/shoppingCarts`, searchDTO);
  }

  get(shoppingCartId) {
    return this.getRequest(`/shoppingCart/${shoppingCartId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/shoppingCart?field=${matchData}`, null);
  }

  addShoppingCart(data) {
    return http.post("/shoppingCart/addShoppingCart", data);
  }

  update(data) {
  	return http.post("/shoppingCart/updateShoppingCart", data);
  }
  
  uploadImage(data,shoppingCartId) {
  	return http.postForm("/shoppingCart/uploadImage/"+shoppingCartId, data);
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

export default new ShoppingCartService();
