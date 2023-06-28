<template>
  <div id="app">
    <dv-full-screen-container class="containerBox">
      <div class="header">
        <h1>物流大数据平台</h1>
      </div>
      <div class="containerBody">
        <div class="leftCharts">
          <div class="weather">
            <dv-border-box-7>
              <weather />
            </dv-border-box-7>
          </div>
          <div class="left1">
            <dv-border-box-7>
              <shoppingMode />
            </dv-border-box-7>
          </div>
          <div class="left2">
            <dv-border-box-7>
              <daysForShipping />
            </dv-border-box-7>
          </div>
        </div>
        <div class="centerMap">
          <dv-border-box-7>
            <mapView style="width: 100%;height: 100%;margin:0 auto;scale: 1.1;padding-top: 3vh;" />
          </dv-border-box-7>
        </div>
        <div class="rightCharts">
          <div class="weather">
            <dv-border-box-7>
              <div style="padding: 5px 0 0 10px;text-align: left;font-weight: 400;">
                <span>实时路况</span>
              </div>
              <location />
            </dv-border-box-7>
          </div>
          <div class="right" style="margin: 2vh 20px;">
            <dv-border-box-7>
              亚洲订单排行(国家)
              <dv-scroll-ranking-board :config="configData" style="width:94%;height:200px;margin: 0 auto;" />
            </dv-border-box-7>
          </div>
          <div class="right2">
            <dv-border-box-7>
              <orderProfit style="width: 100%;height: 100%;" />
            </dv-border-box-7>
          </div>
        </div>
      </div>
    </dv-full-screen-container>
  </div>
</template>

<script>
import mapView from './components/map.vue'
import weather from './components/weather.vue'
import shoppingMode from './components/shoppingMode.vue'
import orderProfit from './components/orderProfit.vue'
import location from './components/location.vue'
import daysForShipping from './components/daysForShipping.vue'

export default {
  name: 'App',
  components: {
    mapView, shoppingMode, orderProfit, weather, location, daysForShipping
  },
  data() {
    return {
      configData: {}
    };
  },
  mounted() {
    this.getOrderCoutry()
  },
  methods: {
    getOrderCoutry() {
      this.$http.get('/api/orderCountry').then(res => {
        res.data.data = res.data.data.slice(0, 20)
        this.configData = { ...res.data, waitTime: 1500 }
      })
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #fff;
}

body {
  padding: 0;
  margin: 0;
  background: url('./assets/true.png');
}

.right {
  width: 28vw;
  height: 29vh;
  margin: 0 20px;
}

.right2 {
  width: 28vw;
  height: 41vh;
  margin: 0 20px;
}

.header {
  width: 100%;
  height: 80px;
  background: url(./assets/title.png) no-repeat;
  background-size: 100% 100%;
  margin: 10px 0;
}

.centerMap {
  width: 40vw;
  height: 85vh;
  margin: 0 auto;
}

.left1 {
  width: 28vw;
  height: 35vh;
  margin: 0 20px;
}

.weather {
  width: 28vw;
  height: 11vh;
  margin: 0 20px 2vh 20px;
  font-size: 18px;
  font-weight: 300;
}

.left2 {
  width: 28vw;
  height: 35vh;
  margin: 2vh 20px;
}

.containerBox {
  display: flex;
  flex-direction: column;
}

.containerBody {
  display: flex;
  justify-content: space-around;
}

.rightCharts {
  width: 30%;
}
</style>
