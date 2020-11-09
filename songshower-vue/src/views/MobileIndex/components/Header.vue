<template>
	<div class="headerDiv">
		<el-menu
			mode="horizontal"
			class="headerMenu"
			background-color="#545c64">
			<div class="header">
				<div class="logoDiv">
					<img src="@/assets/logo.png" class="logo" alt="">
				</div>
				<div class="searchDiv">
					<el-input placeholder="请输入内容" v-model="dataForm.param" class="searchInput" size="mini">
				    <el-select v-model="dataForm.type" slot="prepend" placeholder="请选择" class="searchSelect" size="mini">
				      <el-option label="歌名" value="title"></el-option>
				      <el-option label="歌手" value="artist"></el-option>
				      <el-option label="专辑" value="album"></el-option>
				    </el-select>
				    <el-button slot="append" icon="el-icon-search" size="mini" @click="searchButton"></el-button>
				  </el-input>
				  <el-button class="pc-randomButton" size="mini" @click="$emit('random-flag')"><img src="@/assets/Random.svg" alt="" class="pc-randomSvg"></el-button>
				</div>
				<div class="toolDiv"></div>	
			</div>
		</el-menu>
	</div>	
</template>
<style scoped lang="scss">
	.headerMenu{
		height: 80px;
		.header{
			display: flex;
			align-items:center;
			justify-content:space-between;
			height: 100%;
			.logoDiv{
				margin-left: $mobileMargin;
				.logo{
					width: 50px;
					height: 50px;
				}
			}
			.searchDiv{
				display:flex;
				align-items:center;
				.searchInput{
					width: 500px;
				}
				.searchSelect{
					width: 70px;
				}
				.pc-randomButton{
					margin-left:5px;
					padding: 4px 10px;
					.pc-randomSvg{
						width: 16px;
						height: 16px;
					}
				}
			}
			.toolDiv{
				margin-right:$mobileMargin;
				width: 0px;
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