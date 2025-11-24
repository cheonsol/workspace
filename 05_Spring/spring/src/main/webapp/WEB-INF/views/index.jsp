<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>서울시 공중화장실 위치정보</title>

	<!-- 카카오 지도 API -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=인증키"></script>

	<style>
		.toilet-container {
			max-width: 1200px;
			margin: 50px auto;
			padding: 2rem;
		}

		.toilet-card {
			background: white;
			border-radius: 8px;
			box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
			padding: 2rem;
		}

		.toilet-card h2 {
			text-align: center;
			color: #333;
			margin-bottom: 2rem;
			padding-bottom: 1rem;
			border-bottom: 2px solid #4b89fc;
		}

		.info-area {
			background-color: #f8f9fa;
			padding: 1rem;
			border-radius: 5px;
			margin-bottom: 1.5rem;
			text-align: center;
		}

		.info-area p {
			margin: 0;
			color: #666;
			font-size: 0.95rem;
		}

		.toilet-table {
			width: 100%;
			border-collapse: collapse;
			margin-bottom: 2rem;
		}

		.toilet-table thead {
			background: #4b89fc;
			color: white;
		}

		.toilet-table th,
		.toilet-table td {
			padding: 1rem;
			text-align: center;
			border-bottom: 1px solid #e0e0e0;
		}

		.toilet-table th {
			font-weight: 500;
			font-size: 0.95rem;
		}

		.toilet-table td {
			font-size: 0.9rem;
		}

		.toilet-table tbody tr {
			transition: all 0.2s ease;
		}

		.toilet-table tbody tr:hover {
			background-color: #f5f8ff;
			cursor: pointer;
			transform: translateY(-2px);
			box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
		}

		.badge {
			display: inline-block;
			padding: 0.25rem 0.6rem;
			font-size: 0.75rem;
			border-radius: 12px;
			font-weight: 500;
		}

		.badge-primary {
			background-color: #8329f1;
			color: #1976d2;
		}

		.badge-success {
			background-color: #00a006;
			color: #388e3c;
		}

		.pagination {
			display: flex;
			justify-content: center;
			gap: 0.5rem;
			margin-top: 2rem;
		}

		.pagination .btn {
			min-width: 40px;
		}

		.text-left {
			text-align: left !important;
		}

		.modal {
			display: none;
			position: fixed;
			z-index: 1000;
			left: 0;
			top: 0;
			width: 100%;
			height: 100%;
			background-color: rgba(0,0,0,0.5);
		}

		.modal-content {
			background-color: white;
			margin: 3% auto;
			padding: 0;
			border-radius: 8px;
			width: 95%;
			max-width: 1000px;
			max-height: 90vh;
			overflow: hidden;
		}

		.modal-header {
			border-bottom: 2px solid #4b89fc;
			padding: 1.5rem 2rem;
			display: flex;
			justify-content: space-between;
			align-items: center;
		}

		.modal-header h3 {
			margin: 0;
			color: #333;
		}

		.close {
			font-size: 1.5rem;
			font-weight: bold;
			cursor: pointer;
			color: #666;
			line-height: 1;
		}

		.close:hover {
			color: #000;
		}

		.modal-body-container {
			display: flex;
			max-height: calc(90vh - 80px);
		}

		.modal-info {
			flex: 1;
			padding: 1.5rem 2rem;
			overflow-y: auto;
			max-height: 600px;
		}

		.modal-map {
			flex: 0 0 450px;
			background-color: #f0f0f0;
		}

		#map {
			width: 100%;
			height: 100%;
			min-height: 500px;
		}

		.detail-item {
			margin-bottom: 1rem;
			padding: 0.8rem;
			background-color: #f8f9fa;
			border-radius: 5px;
		}

		.detail-item label {
			font-weight: 600;
			color: #555;
			display: block;
			margin-bottom: 0.3rem;
			font-size: 0.9rem;
		}

		.detail-item p {
			margin: 0;
			color: #333;
		}

		@media (max-width: 768px) {
			.modal-body-container {
				flex-direction: column;
			}
			.modal-map {
				flex: 0 0 300px;
			}
		}
	</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/menubar.jsp" />

<div class="toilet-container">
	<div class="toilet-card">
		<h2>서울시 공중화장실 위치정보</h2>

		<div class="info-area">
			<p>
				<strong>전체 ${pi.listCount}개</strong>의 공중화장실이 등록되어 있습니다.
				현재 <strong>${pi.currentPage}</strong> 페이지 (전체 ${pi.maxPage} 페이지)
			</p>
		</div>

		<table class="toilet-table">
			<thead>
			<tr>
				<th width="60">번호</th>
				<th width="200">화장실명</th>
				<th width="100">구</th>
				<th width="300">주소</th>
				<th width="100">유형</th>
				<th width="120">개방시간</th>
			</tr>
			</thead>
			<tbody>
			<c:choose>
				<c:when test="${not empty toiletList}">
					<c:forEach var="toilet" items="${toiletList}">
						<tr onclick="showDetail(${toilet.objectId})">
							<td>${toilet.objectId}</td>
							<td class="text-left">${toilet.contsName}</td>
							<td>
								<span class="badge badge-primary">${toilet.guName}</span>
							</td>
							<td class="text-left">
								<c:choose>
									<c:when test="${not empty toilet.addrNew}">
										${toilet.addrNew}
									</c:when>
									<c:otherwise>
										${toilet.addrOld}
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<span class="badge badge-success">${toilet.value01}</span>
							</td>
							<td>
								<c:choose>
									<c:when test="${not empty toilet.value02}">
										${toilet.value02}
									</c:when>
									<c:otherwise>
										24시간
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="6" style="padding: 3rem; color: #999;">
							등록된 화장실 정보가 없습니다.
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>

		<div class="pagination">
			<c:if test="${pi.currentPage > 1}">
				<button class="btn btn-primary"
						onclick="location.href='${pageContext.request.contextPath}/?cpage=${pi.currentPage - 1}'">
					&lt; 이전
				</button>
			</c:if>

			<c:forEach var="i" begin="${pi.startPage}" end="${pi.endPage}">
				<c:choose>
					<c:when test="${i == pi.currentPage}">
						<button class="btn btn-outline-primary" disabled>
								${i}
						</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-outline-primary"
								onclick="location.href='${pageContext.request.contextPath}/?cpage=${i}'">
								${i}
						</button>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:if test="${pi.currentPage < pi.maxPage}">
				<button class="btn btn-primary"
						onclick="location.href='${pageContext.request.contextPath}/?cpage=${pi.currentPage + 1}'">
					다음 &gt;
				</button>
			</c:if>
		</div>
	</div>
</div>

<!-- 상세정보 모달 -->
<div id="detailModal" class="modal">
	<div class="modal-content">
		<div class="modal-header">
			<h3>화장실 상세정보</h3>
			<span class="close" onclick="closeModal()">&times;</span>
		</div>
		<div class="modal-body-container">
			<div class="modal-info">
				<div id="modalBody">
					<!-- 상세 정보가 여기에 표시됩니다 -->
				</div>
			</div>
			<div class="modal-map">
				<div id="map"></div>
			</div>
		</div>
	</div>
</div>

<script>
	// 상세정보 표시
	console.log(`${toiletList}`)
	function showDetail(objectId) {
		const toilets = [
			<c:forEach var="toilet" items="${toiletList}" varStatus="status">
			{
				objectId: ${toilet.objectId},
				contsName: '${toilet.contsName}',
				guName: '${toilet.guName}',
				addrNew: '${toilet.addrNew}',
				addrOld: '${toilet.addrOld}',
				coordX: '${toilet.coordX}',
				coordY: '${toilet.coordY}',
				telNo: '${toilet.telNo}',
				value01: '${toilet.value01}',
				value02: '${toilet.value02}',
				value03: '${toilet.value03}',
				value04: '${toilet.value04}',
				value05: '${toilet.value05}',
				value06: '${toilet.value06}',
				value07: '${toilet.value07}',
				value08: '${toilet.value08}',
				value09: '${toilet.value09}'
			}<c:if test="${!status.last}">,</c:if>
			</c:forEach>
		];

		const toilet = toilets.find(t => t.objectId === objectId);
		if (!toilet) return;

		const modalBody = document.getElementById('modalBody');
		// 도로명주소가 있으면 사용, 없으면 지번주소 사용
		const address = (toilet.addrNew && toilet.addrNew.trim() !== '') ? toilet.addrNew : toilet.addrOld;

		// 기본 정보 렌더링
		modalBody.innerHTML = `
                <div class="detail-item">
                    <label>화장실명</label>
                    <p><strong>\${toilet.contsName}</strong></p>
                </div>
                <div class="detail-item">
                    <label>소재지 구</label>
                    <p>\${toilet.guName}</p>
                </div>
                <div class="detail-item">
                    <label>주소</label>
                    <p>\${address}</p>
                </div>
                <div class="detail-item">
                    <label>좌표 (X, Y)</label>
                    <p>\${toilet.coordX}, \${toilet.coordY}</p>
                </div>
            `;

		// 헬퍼 함수: 값이 있는지 확인 (null, undefined, 빈 문자열 체크)
		const hasValue = (value) => value && value.trim() !== '';

		// 추가 정보 조건부 렌더링
		if (hasValue(toilet.telNo)) {
			modalBody.innerHTML += `
                <div class="detail-item">
                    <label>전화번호</label>
                    <p>\${toilet.telNo}</p>
                </div>`;
		}
		if (hasValue(toilet.value01)) {
			modalBody.innerHTML += `
                <div class="detail-item">
                    <label>유형</label>
                    <p>\${toilet.value01}</p>
                </div>`;
		}
		if (hasValue(toilet.value02)) {
			modalBody.innerHTML += `
                <div class="detail-item">
                    <label>개방시간</label>
                    <p>\${toilet.value02}</p>
                </div>`;
		}
		if (hasValue(toilet.value03)) {
			modalBody.innerHTML += `
                <div class="detail-item">
                    <label>소재지 용도</label>
                    <p>\${toilet.value03}</p>
                </div>`;
		}
		if (hasValue(toilet.value04)) {
			modalBody.innerHTML += `
                <div class="detail-item">
                    <label>화장실 현황</label>
                    <p>\${toilet.value04}</p>
                </div>`;
		}
		if (hasValue(toilet.value05)) {
			modalBody.innerHTML += `
                <div class="detail-item">
                    <label>장애인화장실 현황</label>
                    <p>\${toilet.value05}</p>
                </div>`;
		}
		if (hasValue(toilet.value06)) {
			modalBody.innerHTML += `
                <div class="detail-item">
                    <label>편의시설 (기타설비)</label>
                    <p>\${toilet.value06}</p>
                </div>`;
		}
		if (hasValue(toilet.value07)) {
			modalBody.innerHTML += `
                <div class="detail-item">
                    <label>안내표지</label>
                    <p>\${toilet.value07}</p>
                </div>`;
		}
		if (hasValue(toilet.value08)) {
			modalBody.innerHTML += `
                <div class="detail-item">
                    <label>소재지</label>
                    <p>\${toilet.value08}</p>
                </div>`;
		}
		if (hasValue(toilet.value09)) {
			modalBody.innerHTML += `
                <div class="detail-item">
                    <label>비고</label>
                    <p>\${toilet.value09}</p>
                </div>`;
		}

		// 모달 표시
		document.getElementById('detailModal').style.display = 'block';

		// 카카오 지도 초기화 (SDK 로드 후 실행)
		kakao.maps.load(() => {
			initMap(toilet);
		});
	}

	// 카카오 지도 초기화 함수
	function initMap(toilet) {
		const mapContainer = document.getElementById('map');

		// 좌표 값 확인
		const lat = parseFloat(toilet.coordY);
		const lng = parseFloat(toilet.coordX);

		if (isNaN(lat) || isNaN(lng)) {
			mapContainer.innerHTML = '<div style="display: flex; align-items: center; justify-content: center; height: 100%; color: #999;">좌표 정보가 없습니다.</div>';
			return;
		}

		// 기존 지도 내용 초기화
		mapContainer.innerHTML = '';

		// 지도 옵션
		const mapOption = {
			center: new kakao.maps.LatLng(lat, lng),
			level: 3
		};

		// 지도 생성
		const map = new kakao.maps.Map(mapContainer, mapOption);

		// 마커 위치
		const markerPosition = new kakao.maps.LatLng(lat, lng);

		// 마커 생성
		const marker = new kakao.maps.Marker({
			position: markerPosition
		});

		marker.setMap(map);
		console.log(toilet);
		// 인포윈도우 생성
		const iwContent = `<div style="padding:10px; font-size:14px;">` + toilet.contsName + `</div>`;
		const infowindow = new kakao.maps.InfoWindow({
			content: iwContent,
			removable: false
		});

		// 인포윈도우 표시
		infowindow.open(map, marker);
	}

	// 모달 닫기
	function closeModal() {
		document.getElementById('detailModal').style.display = 'none';
	}

	// 모달 외부 클릭시 닫기
	window.onclick = function(event) {
		const modal = document.getElementById('detailModal');
		if (event.target === modal) {
			closeModal();
		}
	}
</script>
</body>
</html>