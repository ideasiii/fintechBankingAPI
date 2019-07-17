$(function () {
	list();


})

function modify(id) {


}

function list() {

	var headers = [{
			title: 'id'
		},
		{
			title: 'token'
		},
		{
			title: '單位'
		}
	];


	Vue.component('data-table', {
		template: '<table class="stripe hover row-border order-column cell-border" role="grid"></table>',
		props: ['data'],
		data() {
			return {
				headers: headers,
				rows: [],
				dtHandle: null
			}
		},
		watch: {
			data(val, oldVal) {
				let vm = this;
				let user = "";
				vm.rows = [];
				// You should _probably_ check that this is changed data... but we'll skip that for this example.
				val.tokenList.forEach(function (item) {
					// Fish out the specific column data for each item in your data set and push it to the appropriate place.
					// Basically we're just building a multi-dimensional array here. If the data is _already_ in the right format you could
					// skip this loop...
					var adminiBtn = "";
					let row = [];

					// adminiBtn += '<a class="button small" href="javascript:void(0)" data-popup="tooltip" title="" onclick="modify(' + item.id + ')"><i class="fas fa-pen"></i></a>';
					// adminiBtn += '<a class="button small" href="javascript:void(0)" data-popup="tooltip" title="" onclick="check(' + item.id + ')"><i class="fas fa-check"></i></a>';


					if (item.user == null) {
						user = "";
					} else {
						user = item.user;
					}

					row.push(item.id);
					row.push(item.token);
					row.push(user);
					// row.push(adminiBtn);
					vm.rows.push(row);

				});

				// Here's the magic to keeping the DataTable in sync.
				// It must be cleared, new rows added, then redrawn!
				vm.dtHandle.clear();
				vm.dtHandle.rows.add(vm.rows);
				vm.dtHandle.draw();
			}
		},
		method: {
			TableInput() {

			}
		},
		mounted() {
			let vm = this;
			// Instantiate the datatable and store the reference to the instance in our dtHandle element.
			vm.dtHandle = $(this.$el).DataTable({
				// Specify whatever options you want, at a minimum these:
				columnDefs: [{
					width: '100px',
					targets: [0]
				}],
				order: [0, "asc"],
				columns: vm.headers,
				data: vm.rows,
				searching: true,
				info: false,

			});

			$('#tableList').on('click', 'tbody td', function () {

				var cell = vm.dtHandle.cell(this);
				var column = vm.dtHandle.column(this);
				var node = cell.node();
				var rowID = $(this).parent().find('td').html().trim()

				if (cell.data() && column.index() == 2) {
					// cell.data('<div class="row"><div class="col-12"><input type="text" name="demo-name" id="demo-name" value="'+cell.data()+'" placeholder="Name"></div></div>').draw;
					// $(node).html('<input type="text" name="demo-name" id="demo-name" value="'+cell.data()+'">');
				} else if ($('#tableList').find('input').length == 0 && column.index() == 2) {
					$(node).append('<div class="row"><div class="col-12"><input type="text" name="demo-name" value="" placeholder="Team"></div></div>');
				}

				$("input").focusout(function () {
					var content = $(this).val();
					
					if (content.length == 0 || content == null) {
						
						cell.data("").draw;
					}else{
						$.ajax({
							url: 'http://http://www.fintechersapi.com/bank/huanan/token/list/' + rowID,
							type: "PATCH",
							dataType: 'json',
							contentType: "application/json",
							data: JSON.stringify({
								'user': content,
								'used': 1
							}),
							statusCode: {
								200: function () {
									// window.location.replace("index.html");
									cell.data(content).draw;
								}
							}
						});
					}

				})


			})
		}
	});

	new Vue({
		el: '#list',
		data: {
			list: []
		},
		mounted() {
			let vm = this;
			$.ajax({
				url: 'http://localhost:8080/bank/huanan/token/list',
				type: "GET",
				dataType: 'json',
				statusCode: {
					200: function () {
						// window.location.replace("index.html");
					}
				},
				success(res) {
					vm.list = res;
				}
			});
		}
	});


}