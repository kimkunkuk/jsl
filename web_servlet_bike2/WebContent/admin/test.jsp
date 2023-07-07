<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>  

<html>  

<head>  

  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>  

  <style type="text/css">  

    table {width:500px;}  

    .hide {display:none;}  

    .show {display:table-row;}  

    .item td {cursor:pointer;}  

   </style>  

</head>  

<body>  

    <table cellspacing="0" border="1" class="recruit">  

        <caption>진행중인 채용</caption>  

        <colgroup>  

            <col width="25%">  

            <col width="25%">  

            <col width="25%">  

            <col width="25%">  

        </colgroup>  

        <thead>  

            <tr class="">  

                <th scope="col">가나다</th>  

                <th scope="col">가나다</th>  

                <th scope="col">가나다</th>  

                <th scope="col">가나다</th>  

            </tr>  

        </thead>  

        <tbody>  

            <tr class="item">  

                <td>아야어</td>  

                <td>아야어</td>  

                <td>아야어</td>  

                <td>아야어</td>  

            </tr>  

            <tr class="hide">  

                <td colspan="4">  

                    1. 모집부분 <br>  

                    [경력] 제휴사업기획 인원 모집 <br> <br>  

                    2. 담당업무 <br>  

                    1) 시장 분석 및 전략 수립 <br>  

                    2) 사업적 지표(데이터)관리 및 분석 <br>  

                    3) 사업 일정 관리 및 내,외부 커뮤니케이션/관리 <br><br>  

                    3. 자격요건 <br>  

                    - 게임 및 게임사업에 대한 비전과 열정 보유 <br>  

                    - 원만한 대인관계 및 커뮤니케이션 능력 보유 <br>  

                    - 전략적인 문제 해결능력 및 데이터 분석 능력 보유 <br>  

                    - 사업/마케팅/기획 업무 능력 보유 <br>  

                    - 책임감이 강하며, 꼼꼼하고 정확한 성격의 소유자  

                </td>  

            </tr>  
        </tbody>  

    </table>  

    <script type="text/javascript">  

        $(function(){  

            var article = (".recruit .show");  

            $(".recruit .item  td").click(function() {  

                var myArticle =$(this).parents().next("tr");  

                if($(myArticle).hasClass('hide')) {  

                    $(article).removeClass('show').addClass('hide');  

                    $(myArticle).removeClass('hide').addClass('show');  

                }  

                else {  

                    $(myArticle).addClass('hide').removeClass('show');  

                }  

            });  

        });  

    </script>  

</body>  

</html>  