<template>
	<div class="pc-headerDiv">
		<el-menu
			mode="horizontal"
			class="pc-headerMenu"
			background-color="#545c64">
			<div class="pc-header">
				<div class="pc-logoDiv">
					<img src="@/assets/logo.png" class="pc-logo" alt="">
				</div>
				<div class="pc-searchDiv">
					<el-input placeholder="请输入内容" v-model="dataForm.param" class="pc-searchInput">
				    <el-select v-model="dataForm.type" slot="prepend" placeholder="请选择" class="pc-searchSelect">
				      <el-option label="歌名" value="title"></el-option>
				      <el-option label="歌手" value="artist"></el-option>
				      <el-option label="专辑" value="album"></el-option>
				    </el-select>
				    <el-button slot="append" icon="el-icon-search" @click="searchButton"></el-button>
				  </el-input>
				  <el-button class="pc-randomButton" @click="$emit('random-flag')"><img src="@/assets/Random.svg" alt="" class="pc-randomSvg"></el-button>
				</div>
				<div class="pc-toolDiv"></div>	
			</div>
		</el-menu>
	</div>	
</template>
<style scoped lang="scss">
	.pc-headerMenu{
		height: 60px;
		.pc-header{
			display: flex;
			align-items:center;
			justify-content:space-between;
			height: 100%;
			.pc-logoDiv{
				margin-left: $pcMargin;
				.pc-logo{
					width: 50px;
					height: 50px;
				}
			}
			.pc-searchDiv{
				display:flex;
				align-items:center;
				.pc-searchInput{
					width: 550px;
				}
				.pc-searchSelect{
					width: 100px;
				}
				.pc-randomButton{
					margin-left:10px;
					padding: 2px 10px;
					.pc-randomSvg{
						width: 32px;
						height: 32px;
					}
				}
			}
			.pc-toolDiv{
				margin-right:$pcMargin;
				width: 200px;
			}
		}
	}
</style>
<script>
	import headerMixin from '@/mixins/headerMixin'
	export default {
		name:'Header',
		mixins:[headerMixin],
	}
</script>