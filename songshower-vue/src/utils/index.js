//判断是否为移动端
export function IsMobile() {
  if(/Android|webOS|iPhone|iPod|iPad|IOS|BlackBerry|XiaoMi|MicroMessenger/i.test(navigator.userAgent)) {
    return true;
  } else {
    return false;
  }
}