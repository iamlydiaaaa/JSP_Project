<div id="navi">
    <ul class="quick-menu">
      <li><a href="#"><i class="fas fa-comment"></i></a></li>
      <li><span>행사 정보</span><a href="#"><i class="fas fa-user"></i></a></li>
      <li><span>날짜 확인</span><a href="#"><i class="fas fa-headset"></i></a></li>
      <li><span>상세 안내</span><a href="#"><i class="fas fa-star"></i></a></li>
      <li><span>리뷰</span><a href="#"><i class="fas fa-calendar-check"></i></a></li>
    </ul>
    <a href="#" class="quick-top"></a>
  </div>

<script>
    $(window).on('scroll', function () {
        han()

        var scNum = $(window).scrollTop()
        if (scNum < 300) {
            $('.quick-menu').css({
                'right': '-100px'
            })
        } else if (scNum >= 300) {
            $('.quick-menu').css({
                'right': '10px'
            })
        }
        if (scNum > 270) {
            $('.pro-wrap>img').css({
                'opacity': '1',
                'transform': 'translateY(0)'
            })
        }
        if (scNum > 470) {
            $('.product .txt').css({
                'opacity': '1',
                'transform': 'translateY(0)'
            })
        }
        if (scNum > 970) {
            $('.promotion .txt').css({
                'opacity': '1',
                'transform': 'translateY(0)'
            })
        }
        if (scNum > 1670) {
            $('.story .txt').css({
                'opacity': '1',
                'transform': 'translateY(0)'
            })
        }
    })
</script>
