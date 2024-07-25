<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 상세</title>
<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 20px;
        }
        h1 {
            color: #333;
        }
        table {
            width: 50%;
            margin-top: 20px;
            border-collapse: collapse;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
            color: #333;
            font-weight: bold;
        }
        td {
            vertical-align: top;
        }
        .button-container {
            margin-top: 20px;
        }
        .button-container button {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .button-container button:hover {
            background-color: #0056b3;
        }
        .back-button {
            margin-top: 20px;
        }
        .back-button button {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 4px;
            font-size: 16px;
        }
        .back-button button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
	<h1>게시판 상세페이지</h1>
	<table>
		<tr>
			<td>게시물 번호</td>
			<td>${board.bId }</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${board.bTitle }</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${board.bContent }</td>
		</tr>
		<tr>
			<td>작성 날짜</td>
			<td>${board.bDateTime }</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${board.bViews }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${board.user.uName}</td>
		</tr>
		
		<div class="button-container">
        	<button onclick="location.href='/updateBoard?b_id=${board.bId }'">수정</button>
        	<button onclick="location.href='/deleteBoard?b_id=${board.bId }'">삭제</button>
    	</div>
	</table>
	
	<div class="back-button">
        <a href="board-list.do">
            <button>돌아가기</button>
        </a>
    </div>
</body>
</html>