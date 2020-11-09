export default {
	props:{
		search:Function,
	},
	data(){
		return {
			dataForm:{
				type:'title',
				param:'',
			},
		}
	},
	methods:{
		searchButton(){
			let type = this.dataForm.type;
			this.search(type,this.dataForm.param)
		}
	}
}