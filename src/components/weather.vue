<template>
     <div class="weatherContainer">
          <span style="font-size: 18px; font-weight: 400;">实时天气<img class="weatherimg" src='../assets/云.png' alt=""/></span>
          
          <div class="f18">{{ weatherData.city }}&nbsp;-&nbsp;{{ weatherData.weather }}&nbsp;-&nbsp;{{
               weatherData.temperature
          }}℃</div>
          <div class="f15">更新时间：{{ weatherData.reporttime }}</div>
          <div id="container" style="width: 40px;height: 40px;opacity: 0;"></div>
     </div>
</template>

<script>
export default {
     name: 'weather',
     data() {
          return {
               weatherData: [],
               imageKey:'天气'
          }   
     },
     mounted() {
          this.getWeather()
     },
     methods: {
          async getWeather() {
               const bdAK = 'VNM7SK0O2MPalBwhukUxDZiigqSDItZc'
               await this.$http.get('/baidu/location/ip?ak=' + bdAK).then(res => {
                    const { adcode } = res.data.content.address_detail
                    this.$http.get('https://restapi.amap.com/v3/weather/weatherInfo?city=' + adcode + '&key=9bd08f5ea6e04968e9ce97888acf22b4').then(res => {
                         this.weatherData = res.data.lives[0]
                    })
               })

          }
     }
}
</script>

<style scoped>
.weatherContainer {
     display: flex;
     flex-direction: column;
     align-items: self-start;
     color: #fff;
     margin-left: 7px;
}
.weatherimg{
     width: 25px;
     height: 25px;
     transform: translateY(6px);
     padding-left: 3px;
}
.f15 {
     font-size: 10px;
     opacity: 0.6;
     margin-top: 10px;
}

.f20 {
     font-size: 17px;
     font-weight: bold;
}

.f18 {
     font-size: 24px;
     margin: 0 auto;
     font-weight: 400;
}
</style>