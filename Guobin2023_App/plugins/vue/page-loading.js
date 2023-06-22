function show() {
    var loadingContainer = document.getElementById('loading-container');
    loadingContainer.style.display = 'flex';
    document.documentElement.style.overflow = 'hidden';
  }

  function hide() {
    var loadingContainer = document.getElementById('loading-container');
    loadingContainer.style.display = 'none';
  }

  // 显示加载动画
  function showLoading(){
    show();
  }

  function hideLoading() {
    // 模拟加载动画持续 2-3 秒
    setTimeout(() => {
        // 加载完成后恢复滚动
        document.documentElement.style.overflow = "auto";
        // 执行其他操作，例如加载数据或隐藏加载动画
        hide();
      }, 0); // 调整延迟时间为 2-3 秒
  }

showLoading();

hideLoading();


