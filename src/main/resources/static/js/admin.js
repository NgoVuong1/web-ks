function openTab(evt, tabName) {
  var i, tabContent, tabButtons;
  
  tabContent = document.getElementsByClassName("tab-content");
  for (i = 0; i < tabContent.length; i++) {
    tabContent[i].classList.remove("active");
  }

  tabButtons = document.getElementsByClassName("tab-button");
  for (i = 0; i < tabButtons.length; i++) {
    tabButtons[i].classList.remove("active");
  }

  document.getElementById(tabName).classList.add("active");
  evt.currentTarget.classList.add("active");
}

window.addEventListener("scroll", () => {
  document.body.classList.toggle("scrolled", window.scrollY > 50);
});


var thongKeList = typeof thongKeList !== 'undefined' ? thongKeList : [
    { loaiBaoCao: 'Doanh thu từ đặt phòng', giaTri: 1500000 },
    { loaiBaoCao: 'Doanh thu từ dịch vụ', giaTri: 800000 },
    { loaiBaoCao: 'Số lượt khách hàng', giaTri: 25000 }
];

var labels = thongKeList.map(function(item) {
    return item.loaiBaoCao;
});
var dataValues = thongKeList.map(function(item) {
    return item.giaTri;
});

var ctx = document.getElementById('chart-container').getContext('2d');

var chartData = {
    labels: labels,
    datasets: [{
        label: 'Doanh Thu',
        data: dataValues,
        backgroundColor: [
            'rgba(255, 99, 132, 0.2)', 
            'rgba(54, 162, 235, 0.2)', 
            'rgba(255, 206, 86, 0.2)'
        ],
        borderColor: [
            'rgba(255, 99, 132, 1)', 
            'rgba(54, 162, 235, 1)', 
            'rgba(255, 206, 86, 1)'
        ],
        borderWidth: 1
    }]
};

var myChart = new Chart(ctx, {
    type: 'bar',
    data: chartData,
    options: {
        scales: {
            y: {
                beginAtZero: true,
                ticks: {
                    callback: function(value) {
                        return value >= 1000000 ? value / 1000000 + 'M' : value;
                    }
                }
            }
        }
    }
});