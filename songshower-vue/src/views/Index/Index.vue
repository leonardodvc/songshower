<template>
	<div class="pc-index">
		<Header @random-flag="random()" :search="search"></Header>
		<div class="pc-content">
			<el-table
	      :data="dataList"
	      style="width: 100%">
	      <el-table-column
	      	type="index">
	      </el-table-column>
	      <el-table-column
	        prop="title"
	        label="歌名">
	      </el-table-column>
	      <el-table-column
	        prop="artist"
	        label="歌手">
	        <template slot-scope="scope">
	        	<el-tag type="success" size="medium" v-for="item in scope.row.artist.split('/')" style="margin-right: 10px;" :key="item">{{item}}</el-tag>
	        </template>
	      </el-table-column>
	      <el-table-column
	        prop="album"
	        label="专辑">
	      </el-table-column>
	    </el-table>
	    <div class="pc-pagination">
	    	<el-pagination
		      @current-change="pageChange"
		      :current-page.sync="pageData.page"
		      :page-size="pageData.limit"
		      layout="total, prev, pager, next"
		      :total="total">
		    </el-pagination>
	    </div>
		</div>
	</div>
</template>
<style lang="scss" scoped>
	.pc-index{
		.pc-content{
			margin:$pcMargin;
			.pc-pagination{
				margin-top:10px;
				text-align:end;
			}
		}
	}
</style>
<script>
	import indexMixin from '@/mixins/index'
	import Header from '@/views/Index/components/Header'
	export default {
		name:'Index',
		mixins:[indexMixin],
		components:{
			Header,
		},
		data(){
			return {
			}
		},
		methods:{
		},

	}
</script>