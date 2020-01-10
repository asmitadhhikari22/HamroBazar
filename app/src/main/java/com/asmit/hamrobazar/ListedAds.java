package com.asmit.hamrobazar;


    public class ListedAds {

        public ListedAds(String adName, String adprice, int imagesId, String producttype, String image) {
            this.adName = adName;
            Adprice = adprice;
            this.imagesId = imagesId;
            Producttype = producttype;
            this.image = image;
        }

        public String getAdName() {
            return adName;
        }

        public void setAdName(String adName) {
            this.adName = adName;
        }

        public String getAdprice() {
            return Adprice;
        }

        public void setAdprice(String adprice) {
            Adprice = adprice;
        }

        public int getImagesId() {
            return imagesId;
        }

        public void setImagesId(int imagesId) {
            this.imagesId = imagesId;
        }

        public String getProducttype() {
            return Producttype;
        }

        public void setProducttype(String producttype) {
            Producttype = producttype;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        private String adName;
        private  String Adprice;
        private  int imagesId;
        private  String Producttype;
        private  String image;
    }


