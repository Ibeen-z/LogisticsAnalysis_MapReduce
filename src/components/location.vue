<template>
     <div class="locationBox">
          <div>
               <span style="font-size: 23px;margin: 0 auto;font-weight: 400;" v-if="!isloading">{{ roadTraffic }}</span>
               <span style="font-size: 23px;margin: 0 auto;font-weight: 400;" v-else>加载中,请稍后...</span>
          </div>
          <div style="text-align: left;padding-left: 3px;">
               <img class="locationImg" src="../assets/location.png" alt="">
               <span style=" font-size: 13px; opacity: 0.7;" v-if="!isloading">{{ location }}</span>
               <span style=" font-size: 13px; opacity: 0.7;" v-else>正在定位中...</span>
          </div>
     </div>
</template>

<script>
export default {
     name: 'location',
     data() {
          return {
               lng: '',
               lat: '',
               roadTraffic: '',
               location: '',
               isloading: true
          }
     },
     mounted() {
          this.initloaction()
     },
     methods: {
          initloaction() {
               let that = this
               var map = new BMapGL.Map('container'); // 创建Map实例
               var point = new BMapGL.Point(116.331398, 39.897445);
               map.centerAndZoom(point, 12);
               var geolocation = new BMapGL.Geolocation();
               geolocation.getCurrentPosition(function (r) {
                    if (this.getStatus() == BMAP_STATUS_SUCCESS) {
                         var mk = new BMapGL.Marker(r.point);
                         map.addOverlay(mk);
                         map.panTo(r.point);
                         this.lng = r.point.lng
                         this.lat = r.point.lat
                         // var myGeo = new BMapGL.Geocoder();
                         that.$http.get(`/baidu/reverse_geocoding/v3/?ak=VNM7SK0O2MPalBwhukUxDZiigqSDItZc&output=json&coordtype=wgs84ll&location=${r.point.lat},${r.point.lng}`).then(res1 => {
                              let {street,city} = res1.data.result.addressComponent
                              that.location = res1.data.result.formatted_address
                              if(!street || street===''){
                              street = '花燕路'
                         }
                         console.log('street',street);
                              that.$http.get(`/baidu/traffic/v1/road?road_name=${street}&city=${city}&ak=VNM7SK0O2MPalBwhukUxDZiigqSDItZc`).then(res2 => {
                                        that.roadTraffic = res2.data.description
                                        that.isloading = false
                                   })
                         })
                         // // 根据坐标得到地址描述    
                         // myGeo.getLocation(new BMapGL.Point(r.point.lng, r.point.lat), function (result) {
                         //      console.log('', result);
                         //      that.location = result.address
                         //      if (result) {

                         //      }
                         // });
                    }
               })
          },
     },
}
</script>

<style scoped>
.locationImg {
     width: 24px;
     height: 24px;
     transform: translateY(5px);
}

.locationBox {
     display: flex;
     flex-direction: column;
}
</style>