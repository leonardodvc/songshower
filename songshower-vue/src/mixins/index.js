import { show } from '@/api/index'
export default {
	props:{
		username:String,
	},
	data(){
		return {
			dataForm:{},
			dataList:[],
			data:[],
			mixinOptions:{
				createIsNeed:true,
			},
			pageData: {
        page: 1,
        limit: 10
      },
      total: 0,
      param:{
				username:this.username,
			}
		}
	},
	created () {
		if (this.mixinOptions.createIsNeed) {
			let data = {
				type:'none',
				param:'',
				randomFlag:false,
			}
			this.getData(data)
		}
	},
	methods:{
		async getData(data){
			let res = await show(data,this.param.username)
			this.data = res
			this.total = this.data.length
			this.pageData.page = 1
			this.dataList = this.data.slice(this.pageData.page * this.pageData.limit - this.pageData.limit,this.pageData.page * this.pageData.limit)
		},
		search(type,param){
			let data = {
				type:type,
				param:param,
				randomFlag:false,
			}
			this.getData(data)
		},
		random(){
			let data = {
				type:'none',
				param:'',
				randomFlag:true,
			}
			this.getData(data)
		},
		pageChange(val){
			this.dataList = this.data.slice(this.pageData.page * this.pageData.limit - this.pageData.limit,this.pageData.page * this.pageData.limit)
		}
	}
}