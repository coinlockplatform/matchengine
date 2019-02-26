<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	String auth=(String)session.getAttribute("auth");
	if(auth==null || !auth.equals("1")){
		session.invalidate();
		response.sendRedirect(request.getContextPath() + "/");
		return;
	}
%>
<jsp:include page="/includes/header.jsp" />
<script src="/global_assets/js/demo_pages/form_checkboxes_radios.js"></script>
<%-- <script src="/global_assets/js/plugins/notifications/pnotify.min.js"></script> --%>
<script src="/global_assets/js/plugins/notifications/jgrowl.min.js"></script>
<script src="/global_assets/js/plugins/notifications/noty.min.js"></script>
<script src="/global_assets/js/plugins/visualization/echarts/echarts.min.js"></script>


<script src="/assets/js/dashboard.js"></script>

<jsp:include page="/includes/nav/nav_loggedin.jsp" />
	<!-- Page header -->
	<div class="page-header mb-0">
		<div class="page-header-content header-elements-md-inline">


			<div class="header-elements d-none py-0 mb-3 mb-md-0">
				<div class="breadcrumb">
					<a href="/" class="breadcrumb-item"><i class="icon-home2 mr-2"></i> Home</a>
					<span class="breadcrumb-item active">Dashboard</span>
				</div>
			</div>
		</div>
	</div>
	<!-- /page header -->

	<!-- Page content -->
	<div class="page-content">
		<!-- Main content -->
		<div class="content-wrapper">
			<!-- Content area -->
			<div class="content">
				<!-- Dashboard content -->
				<div class="row">
					<div class="col-xl-12 mt-0">




						<!-- TOP ROW -->

						<div class="row">
							<!-- 
								################################################
								BALANCES CARD 
								################################################
							-->
							<div class="col">
								<div class="rounded py-1 px-1 mb-1">
									<div class="card border-top-1 border-top-primary top-rounded-0">
										<div class="card-header bg-white  header-elements-inline">
											<h6 class="card-title">Balances</h6>
											<div class="header-elements">
												<span class="font-weight-bold text-primary-600 ml-2">$76,878.59</span>
												<div class="list-icons ml-3">
													<a class="list-icons-item" data-action="collapse"></a>
													<a class="list-icons-item" data-action="reload"></a>
													<a class="list-icons-item" data-action="remove"></a>
												</div>
											</div>
										</div>

										<div class="table-responsive">
											<table class="table text-nowrap">
												<thead>
													<tr>
														<th class="w-auto">Asset</th>
														<th>Holdings</th>
														<th>Pending +/-</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td>
															<div class="d-flex align-items-center">
																<div class="mr-3">
																	<a href="#">
																		<img src="/global_assets/images/crypto-logos/bitcoin.png" class="rounded-circle" width="32" height="32" alt="">
																	</a>
																</div>
																<div>
																	<a href="#" class="text-default font-weight-semibold">Bitcoin</a>
																	<div class="text-muted font-size-sm">
																		BTC
																	</div>
																</div>
															</div>
														</td>
														<td>
															<span class="text-muted font-size-sm">12.24048303</span>
															<div class="text-muted font-size-sm"><i class="icon-coin-dollar font-size-sm mr-1"></i>63,6379.90</div>
														</td>
														<td>
															<h6 class="text-secondary font-size-sm mb-0">1.92693450</h6>
														</td>
													</tr>

													<tr>
														<td>
															<div class="d-flex align-items-center">
																<div class="mr-3">
																	<a href="#">
																		<img src="/global_assets/images/crypto-logos/coinlock.png" class="rounded-circle" width="32" height="32" alt="">
																	</a>
																</div>
																<div>
																	<a href="#" class="text-default font-weight-semibold">Lockcoin</a>
																	<div class="text-muted font-size-sm">
																		LOCK
																	</div>
																</div>
															</div>
														</td>
														<td>
															<span class="text-muted font-size-sm">124,332.0346725</span>
															<div class="text-muted font-size-sm"><i class="icon-coin-dollar font-size-sm mr-1"></i>9,054.50</div>
														</td>
														<td>
															<h6 class="text-secondary font-size-sm mb-0">0</h6>
														</td>
													</tr>

													<tr>
														<td>
															<div class="d-flex align-items-center">
																<div class="mr-3">
																	<a href="#">
																		<img src="/global_assets/images/crypto-logos/ripple.png" class="rounded-circle" width="32" height="32" alt="">
																	</a>
																</div>
																<div>
																	<a href="#" class="text-default font-weight-semibold">Ripple</a>
																	<div class="text-muted font-size-sm">
																		XRP
																	</div>
																</div>
															</div>
														</td>
														<td>
															<span class="text-muted font-size-sm">13,578,599.03482</span>
															<div class="text-muted font-size-sm"><i class="icon-coin-dollar font-size-sm mr-1"></i>6,305.00</div>
														</td>
														<td>
															<h6 class="text-secondary font-size-sm mb-0">0</h6>
														</td>
													</tr>

													<tr>
														<td>
															<div class="d-flex align-items-center">
																<div class="mr-3">
																	<a href="#">
																		<img src="/global_assets/images/crypto-logos/litecoin.png" class="rounded-circle" width="32" height="32" alt="">
																	</a>
																</div>
																<div>
																	<a href="#" class="text-default font-weight-semibold">Litecoin</a>
																	<div class="text-muted font-size-sm">
																		LTC
																	</div>
																</div>
															</div>
														</td>
														<td>
															<span class="text-default font-size-sm">34.23424</span>
															<div class="text-muted font-size-sm"><i class="icon-coin-dollar font-size-sm mr-1"></i>55.00</div>
														</td>
														<td>
															<h6 class="text-secondary font-size-sm mb-0">24.0403371</h6>
														</td>
													</tr>

													<tr>
														<td>
															<div class="d-flex align-items-center">
																<div class="mr-2">
																	<a href="#">
																		<img src="/global_assets/images/crypto-logos/ethereum.png" class="rounded-circle" width="32" height="32" alt="">
																	</a>
																</div>
																<div>
																	<a href="#" class="text-default font-weight-semibold">Ethereum</a>
																	<div class="text-muted font-size-sm">
																		ETH
																	</div>
																</div>
															</div>
														</td>
														<td>
															<span class="text-muted font-size-sm">137,586.3453</span>
															<div class="text-muted font-size-sm"><i class="icon-coin-dollar font-size-sm mr-1"></i>22.50</div>
														</td>
														<td>
															<h6 class="text-secondary font-size-sm mb-0">22.46005</h6>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
							<!-- END BALNACES CARD -->



										<%-- <div class="row">
											<div class="col-3">
												<input type="text" class="form-control" placeholder=".col-3">
											</div>

											<div class="col-4">
												<input type="text" class="form-control" placeholder=".col-4">
											</div>

											<div class="col-5">
												<input type="text" class="form-control" placeholder=".col-5">
											</div>
										</div> --%>
							<!-- 
								################################################
								TRADE CARD 
								################################################
							-->
							<div class="col">
								<div class="py-1 px-1 mb-1">
									<div class="card border-top-1 border-top-primary top-rounded-0">
										<div class="card-header bg-white header-elements-inline">
											<h6 class="card-title">Trade</h6>
											<div class="header-elements">
												<div class="list-icons">
													<a class="list-icons-item" data-action="collapse"></a>
													<a class="list-icons-item" data-action="reload"></a>
													<a class="list-icons-item" data-action="remove"></a>
												</div>
											</div>
										</div>
										<div class="card-body">
											<form id='trade-form' class="form-group d-flex row">														
												<div class="col-lg-6 col-sm-12 col-md-12">
													<select data-placeholder="Select an asset" id='asset_selection' name='asset' class="form-control select-icons" data-fouc>
														<optgroup label="Select an asset">
															<option value="btc" data-icon="btc">Bitcoin</option>
															<option value="ltc" data-icon="ltc">Litecoin</option>
															<option value="xrp" data-icon="xrp">Ripple</option>
															<option value="eth" data-icon="eth">Ethereum</option>
														</optgroup>
													</select>
												</div>
												<div class="col-lg-6 col-sm-12 col-md-12 text-center">
													<input class='form-control col-form-label' id='trade_amount' name='amount' type='text' placeholder='Amount    i.e 2.3084'>
												</div>
												<div class="row d-flex mt-2 ml-1">
													<div class='col-md-7 col-sm-12'>
														<input type="checkbox" id='trade_selector' name="trade-selector" data-on-color="success" data-off-color="primary" data-on-text="Give 3X Protection" data-off-text="Get Protection" class="form-check-input-switch" checked="checked" checked>
													</div>
													<div class='col-md-5 col-sm-12 wmin-200'>
														<div class='ml-2 mt-2 d-flex'>
															<input type="checkbox" id='auto_trade' name='auto-trade' class="form-check-input-styled-primary d-inline" data-fouc> 
															<span class='ml-1'>Auto Trade</span>
														</div>
													</div>
												</div>
												<%-- <div class='col-lg-12 amountDesc mt-2' id='amountDesc'>
													Grow your BTC faster by providing peer to peer 3X price protection of Bitcoin by using our automated match engine.
												</div> --%>
												<div id='trade_e' class='text-danger ml-2'></div>
												<div class="col-lg-12 text-center mt-2">
														<button id='tradebtn' type="submit" class="btn btn-outline-primary">Place 3X Protection Order</button>
												</div>
											</form>
											<div class="media-body">
												<ul class="nav nav-tabs nav-tabs-bottom">
													<li class="nav-item"><a href="#basic-tab1" class="nav-link active" data-toggle="tab">Give Protection</a></li>
													<li class="nav-item"><a href="#basic-tab2" class="nav-link" data-toggle="tab">Get Protection</a></li>
													<li class="nav-item"><a href="#basic-tab3" class="nav-link" data-toggle="tab">Auto Trade</a></li>
												</ul>
												<div class="tab-content text-muted">
													<div class="tab-pane fade show active" id="basic-tab1">
														When you give protection you are essentially be matched with another trader. If the price increases, you will increase 3 times as fast as all trades
														are 3x. If the price decreases, you will cover the difference.
													</div>
													<div class="tab-pane fade" id="basic-tab2">
														When you get protection you are locking in the price of your asset in USD value. If the price goes down, you will essentially gain more of the asset
														you are protecting
													</div>
													<div class="tab-pane fade" id="basic-tab3">
														Auto trading is a feature which uses our algorithims to trade your asset with other users with the main goal of increasing your total value.
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>


						</div><!-- END TOP ROW -->
						<!-- SECOND ROW -->
						<div class="row">




							<div class="col">
								<div class="rounded py-1 px-1 mb-1">
									<div class="card border-top-1 border-top-primary top-rounded-0">
										<div class="card-header bg-white header-elements-inline">
											<h6 class="card-title">LOCK Coin Remaining</h6>
											<div class="header-elements">
												<span class="font-weight-bold text-primary-600 ml-2">903,456 LOCK</span>
												<div class="list-icons ml-3">
													<a class="list-icons-item" data-action="collapse"></a>
													<a class="list-icons-item" data-action="reload"></a>
													<a class="list-icons-item" data-action="remove"></a>
												</div>
											</div>
										</div>
										<div id="lockLeft" style="width: 500px; height: 350px;"></div>
									</div>
								</div>
							</div>










							<div class="col">
								<div class="rounded py-1 px-1 mb-1">
									<div class="card border-top-1 border-top-primary top-rounded-0">
										<div class="card-header bg-white header-elements-inline">
											<h6 class="card-title">Trade LOCK</h6>
											<div class="header-elements">
												<div class="list-icons">
													<a class="list-icons-item" data-action="collapse"></a>
													<a class="list-icons-item" data-action="reload"></a>
													<a class="list-icons-item" data-action="remove"></a>
												</div>
											</div>
										</div>

										<div class="card-body text-center justify-content-md-center row">
											<div class='col-lg-5 col-md-12 border-grey-300 border-1 rounded p-4 m-2 wmin-200'>
												<input class='form-control' id='amount' name='amount' type='text' placeholder='0.00'>
												<button type="button" class="btn btn-outline-success mt-2">BUY</button>
												<div class='text-muted font-weight-light font-size-sm mt-1'>1234 LOCK = 0.0232573 BTC</div>
											</div>
											<div class='col-lg-5 col-md-12 border-grey-300 border-1 rounded p-4 m-2 wmin-200'>
												<input class='form-control' id='amount' name='amount' type='text' placeholder='0.00'>
												<button type="button" class="btn btn-outline-danger mt-2">SELL</button>
												<div class='text-muted font-weight-light font-size-sm mt-1'>1234 LOCK = 0.0232573 BTC</div>
											</div>
										</div>
									</div>
								</div>
							</div>
















							<div class="col">
								<div class="rounded py-1 px-1 mb-1">
									<div class="card border-top-1 border-top-primary top-rounded-0">
										<div class="card-header bg-white  header-elements-inline">
											<h6 class="card-title">Recent Logins</h6>
											<div class="header-elements">
												<div class="list-icons">
													<a class="list-icons-item" data-action="collapse"></a>
													<a class="list-icons-item" data-action="reload"></a>
													<a class="list-icons-item" data-action="remove"></a>
												</div>
											</div>
										</div>
										<div class="card-body">
											<div class="list-feed border-0">

											</div>
										</div>
									</div>
								</div><!-- END SECOND ROW -->

	
						<div class="row">
								<div class="col tradingview-widget-container card-body tradingview-widget-container tradingview-widget-copyright"><a href="https://www.tradingview.com/symbols/BITSTAMP-BTCUSD/" rel="noopener" target="_blank"></div>
								<script type="text/javascript" src="https://s3.tradingview.com/tv.js"></script>
								<script type="text/javascript">
									new TradingView.widget(
									{
										"autosize": true,
										"symbol": "BITSTAMP:BTCUSD",
										"timezone": "Etc/UTC",
										"theme": "Light",
										"style": "1",
										"locale": "en",
										"toolbar_bg": "#f1f3f6",
										"enable_publishing": false,
										"hide_top_toolbar": true,
										"range": "1m",
										"save_image": false,
										"container_id": "tradingview_b3e67"
									}
									);
								</script>
						</div>



					</div>
				</div>
				<!-- /dashboard content -->
			</div>
			<!-- /content area -->
			<!-- / End every page content-->
		</div>
		<!-- /main content -->
	</div>
	<!-- /page content -->
<jsp:include page="/includes/footer.jsp" />