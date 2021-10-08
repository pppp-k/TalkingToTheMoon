import "./App.css";
import axios from "axios";
import styled from "styled-components";
import React from "react";

//左侧边栏固定部件
const LeftProfile = styled.div`
  position: absolute;
  top: 0;
  left: 0;
  transform: translate(-100%, 0);
  margin: 0;
  padding: 0;
  width: 180px;
  height: 300px;
  border: 1px solid black;
  box-sizing: border-box;
`;
//左侧边栏固定部件

//广告左右朦布部件
const BannerLR = styled.button`
  position: absolute;
  width: 5%;
  height: 100%;
  top: 0;
  background-color: rgba(0, 0, 0, 0.6);
  z-index: 999;
  filter: blur(2px);
  border: 0;
  cursor: pointer;
  outline: none;
`;
//广告左右朦布部件
// 帖子部件
const InvitationHead = styled.div`
  display: inline-block;
  bottom: 0;
`;

const InvitationFooter = styled.button`
  cursor: pointer;
  background-color: transparent;
  border: 0;
  padding: 2px 0;
  outline: none;
  border-radius: 8px;
  flex: 1;
  color: #000;
  user-select: none;
  :hover {
    background-color: #ccc;
    transition: all 0.5s;
  }
`;

const InvitationSummary = styled.p`
  margin: 0;
  padding: 0 20px;
  min-height: 30px;
  max-height: 45px;
  line-height: 15px;
  font-size: 13px;
  text-align: left;
  overflow: hidden;
  white-space: pre-wrap;
`;
// 帖子部件
// 帖子组件
class InvitationList extends React.Component {
  render() {
    return (
      <div
        style={{
          margin: "0",
          marginBottom: "15px",
          paddingTop: "5px",
          border: "1px solid black",
          borderRadius: "8px",
          boxShadow: "2px 2px rgba(0,0,0,.6)",
        }}
      >
        <div
          style={{
            padding: "0 10px",
            position: "relative",
            textAlign: "left",
            boxSizing: "border-box",
          }}
        >
          <InvitationHead>
            {/* 头像 */}
            {/* 跳转用户主页 */}
            <a href={this.props.userHome} title="点击进入用户主页">
              <img
                style={{
                  width: "50px",
                  height: "50px",
                  borderRadius: "25px",
                }}
                src={this.props.photo}
                alt="Profile photo"
              />
            </a>
          </InvitationHead>
          <InvitationHead style={{ marginLeft: "8px" }}>
            {/* 跳转用户主页 */}
            {/* 昵称 */}
            <div
              style={{
                height: "20px",
                lineHeight: "20px",
              }}
            >
              <a
                style={{
                  textDecoration: "none",
                  fontSize: "14px",
                  fontWeight: "600",
                  color: "black",
                  letterSpacing: "1px",
                }}
                href="#"
                title="点击进入用户主页"
              >
                {this.props.petName}
              </a>
            </div>

            {/* 发送时间 */}
            <div
              style={{
                height: "14px",
                lineHeight: "14px",
                fontSize: "12px",
                color: "#ccc",
                userSelect: "none",
              }}
            >
              {this.props.postDate}
            </div>
          </InvitationHead>
        </div>

        {/* 标题 */}
        <h4
          style={{
            padding: "0 20px",
            textAlign: "left",
            margin: "0",
            height: "18px",
            lineHeight: "18px",
            fontSize: "16px",
            fontWeight: "500px",
          }}
        >
          <a
            style={{ textDecoration: "none", color: "#000" }}
            href={this.props.invitationHome}
            title="点击查看该帖子所有信息"
          >
            {this.props.title}
          </a>
        </h4>
        {/* 简介 */}
        <InvitationSummary
          style={{
            marginTop: "2px",
            padding: "0 20px",
            minHeight: "19px",
            maxHeight: "37px",
            lineHeight: "19px",
            fontSize: "14px",
            textAlign: "left",
            overflow: "hidden",
            whiteSpace: "pre-wrap",
          }}
        >
          {this.props.summary}
        </InvitationSummary>

        <div
          style={{
            margin: "0",
            width: "100%",
            display: "flex",
            flexDirection: "row",
          }}
        >
          {/* 转发 */}
          <InvitationFooter>Repost（{this.props.numRepost}）</InvitationFooter>
          {/* 评论 */}
          <InvitationFooter>
            Comment（{this.props.numComment}）
          </InvitationFooter>
          {/* 点赞 */}
          <InvitationFooter>Likes（{this.props.numLikes}）</InvitationFooter>
        </div>
      </div>
    );
  }
}
// 帖子组件

//左侧边栏个人信息part
class ProfilePart extends React.Component {
  render() {
    return (
      <LeftProfile>
        {/* 头像以及名称部分开始 */}
        <div
          style={{
            marginTop: "1px",
            padding: "2px",
            borderBottom: "1px solid black",
          }}
        >
          <div
            style={{
              display: "inline-block",
              padding: "0",
              width: "60px",
              height: "60px",
              boxSizing: "border-box",
              border: "1px solid black",
              overflow: "hidden",
            }}
          >
            profilePhoto
          </div>
          <div
            style={{
              display: "inline-block",
              width: "100px",
              height: "25px",
              marginLeft: "2px",
              boxSizing: "border-box",
              border: "1px solid black",
              overflow: "hidden",
            }}
          >
            loginUserName
          </div>
        </div>
        {/* 头像以及名称部分结束 */}

        <div></div>
      </LeftProfile>
    );
  }
}
//左侧边栏个人信息part

class App extends React.Component {
  state = {
    // 网页基础信息
    // 广告板块
    banner: [
      {
        title: "妖精的尾巴",
        link: "https://img1.baidu.com/it/u=1132682573,2516732668&fm=26&fmt=auto",
        ad: "",
      },
      {
        title: "翼年代记",
        link: "https://img1.baidu.com/it/u=3445650600,2718025832&fm=26&fmt=auto",
        ad: "",
      },
      {
        title: "我们大家的河合庄",
        link: "https://img1.baidu.com/it/u=3938357233,1259773400&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=281",
        ad: "",
      },
    ],
    bannerX: 0,
    // 公告栏板块
    warning: [
      {
        title: "1关于禁止使用某些用词的修复",
        link: "http://www.baidu.com",
      },
      {
        title: "2禁止使用其他登陆方式的bug修复",
        link: "http://www.qq.com",
      },
      {
        title: "3警告3,不可遏抑攻击服务器",
        link: "http://www.baidu.com",
      },
      {
        title: "4警告4,账号威胁问题",
        link: "http://www.qq.com",
      },
      {
        title: "5警告,5555555",
        link: "http://www.qq.com",
      },
    ],
    warningY: 0,
    //发表帖子板块
    invitationPost: "",
    // 用户设置个性化背景
    backgroundUrl: "",
    // 判断用户是否登录，假如为false，立刻返回登录注册页面
    loginOrNot: "true",
    // 评论渲染
    invitation: [
      {
        postUid: "1",
        photo:
          "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbpic.588ku.com%2Felement_origin_min_pic%2F18%2F08%2F24%2F05dbcc82c8d3bd356e57436be0922357.jpg&refer=http%3A%2F%2Fbpic.588ku.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1635675379&t=b7bdbe07a12a782ddb0f82231f284aff",
        petName: "Jerry",
        postDate: "2021/10/1 17:52",
        title: "The first time to test the program",
        summary: "Now Let's check together",
        numRepost: 0,
        numComment: 1,
        numLikes: 1,
        userHome: "www.baidu.com",
        invitationHome: "www.qq.com",
      },
      {
        postUid: "2",
        photo:
          "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbpic.588ku.com%2Felement_origin_min_pic%2F18%2F08%2F24%2F05dbcc82c8d3bd356e57436be0922357.jpg&refer=http%3A%2F%2Fbpic.588ku.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1635675379&t=b7bdbe07a12a782ddb0f82231f284aff",
        petName: "Jerry",
        postDate: "2021/10/1 17:52",
        title: "The first time to test the program",
        summary:
          "Thursday's vote on the infrastructure bill was postponed and it was unclear when another attempt to hold a vote would be made.\nMr Biden's visit to Capitol Hill on Friday saw him meet privately with House Democrats in a bid to break the deadlock.\nThursday's vote on the infrastructure bill was postponed and it was unclear when another attempt to hold a vote would be made.",
        numRepost: 0,
        numComment: 1,
        numLikes: 1,
        userHome: "www.google.com",
        invitationHome: "www.qq.com",
      },
      {
        postUid: "3",
        photo:
          "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbpic.588ku.com%2Felement_origin_min_pic%2F18%2F08%2F24%2F05dbcc82c8d3bd356e57436be0922357.jpg&refer=http%3A%2F%2Fbpic.588ku.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1635675379&t=b7bdbe07a12a782ddb0f82231f284aff",
        petName: "Jerry",
        postDate: "2021/10/1 17:52",
        title: "The first time to test the program",
        summary: "www.baidu.com",
        numRepost: 0,
        numComment: 1,
        numLikes: 8,
        userHome: "www.baidu.com",
        invitationHome: "www.qq.com",
      },
    ],
  };

  // 可控组件
  handleForm = (e) => {
    const target = e.target;
    const value = target.value;
    const name = target.name;
    this.setState({
      [name]: value,
    });
  };

  //改变state---warningY的值文字上下移动
  warningYChange = () => {
    let y = this.state.warningY;
    let length = this.state.warning.length;
    if (y > (length - 1) * -26) {
      //26为警告板块div的高度
      this.setState({ warningY: this.state.warningY - 26 });
    } else {
      this.setState({ warningY: 0 }); //重置
    }
  };

  //改变state---bannerX的值图片左右移动
  bannerXChange = () => {
    //将字符串转换成数字
    let x = parseFloat(this.state.bannerX);
    let length = this.state.banner.length;
    let lengthMax = -((length - 1) / length) * 100;
    //length为增量阈值
    if (x > lengthMax) {
      x -= 100 / length;
      //+“%”让数字转换成带“%”的字符串
      x += "%";
      this.setState({ bannerX: x });
    } else {
      this.setState({ bannerX: 0 }); //重置
    }
  };

  // 公告栏板块计时器启动
  warningIntervalNew = () => {
    const warningInterval = setInterval(() => {
      this.warningYChange();
    }, 4000);
    this.setState({ warningInterval: warningInterval });
    // console.log("现在warning计时器已经启动");
  };
  // 公告栏板块计时器关闭
  warningIntervalOut = () => {
    clearInterval(this.state.warningInterval);
    // console.log("现在warning计时器已经移除");
  };
  // 广告板块计时器启动
  bannerIntervalNew = () => {
    const bannerInterval = setInterval(() => {
      this.bannerXChange();
    }, 4000);
    this.setState({ bannerInterval: bannerInterval });
    // console.log("现在banner计时器已经启动");
  };
  // 广告板块计时器关闭
  bannerIntervalOut = () => {
    clearInterval(this.state.bannerInterval);
    // console.log("现在banner计时器已经移除");
  };
  //广告右移按钮
  bannerRightMove = () => {
    this.bannerIntervalOut();
    this.bannerXChange();
    this.bannerIntervalNew();
  };
  //广告左移按钮
  bannerLeftMove = () => {
    this.bannerIntervalOut();
    let x = parseFloat(this.state.bannerX);
    let length = this.state.banner.length;
    let lengthMax = -((length - 1) / length) * 100;
    if (x >= 0) {
      x = lengthMax;
      //+“%”让数字转换成带“%”的字符串
      x += "%";
      this.setState({ bannerX: x });
    } else {
      x += 100 / length;
      //+“%”让数字转换成带“%”的字符串
      x += "%";
      this.setState({ bannerX: x });
    }
    this.bannerIntervalNew();
  };

  componentDidMount() {
    // 公告栏板块计时器
    this.warningIntervalNew();
    // 广告板块计时器
    this.bannerIntervalNew();
  }

  componentWillUnmount() {
    this.bannerIntervalOut();
    this.warningIntervalOut();
  }

  render() {
    return (
      <div
        style={{
          width: "100vw",
          height: "100vh",
          margin: "0",
          padding: "0",
          boxSizing: "border-box",
        }}
      >
        <div className="App" style={{ boxSizing: "border-box" }}>
          {/* 此处开始装网页内容 */}

          {/* 中心板块 */}
          <div
            style={{
              position: "relative",
              minWidth: "550px",
              maxWidth: "750px",
              margin: "0 auto",
              paddingLeft: "3px",
              paddingRight: "3px",
              boxSizing: "border-box",
              marginTop: "50px",
            }}
          >
            {/* 侧边栏板块开始 */}

            {/* 左侧边栏开始 */}
            <ProfilePart></ProfilePart>
            {/* 左侧边栏结束 */}

            {/* 右侧边栏开始 */}
            {/* 右侧边栏结束 */}

            {/* 侧边栏板块结束 */}

            {/* 中央内容板块开始 */}
            {/* 广告板块 */}
            <div
              style={{
                width: "100%",
                height: "250px",
                boxSizing: "border-box",
              }}
            >
              <div
                style={{
                  position: "relative",
                  width: "100%",
                  height: "100%",
                  boxSizing: "border-box",
                  overflowX: "hidden",
                }}
              >
                {/* 广告栏左移按钮 */}
                <BannerLR
                  style={{ left: "0" }}
                  onClick={() => {
                    this.bannerLeftMove();
                  }}
                ></BannerLR>
                {/* 广告栏右移按钮 */}
                <BannerLR
                  style={{ right: "0" }}
                  onClick={() => {
                    this.bannerRightMove();
                  }}
                ></BannerLR>
                <div
                  style={{
                    position: "absolute",
                    width: `${this.state.banner.length * 100}%`,
                    height: "100%",
                    boxSizing: "border-box",
                    transform: `translate(${this.state.bannerX},0)`,
                    transition: ".8s",
                    overflow: "hidden",
                  }}
                >
                  {this.state.banner.map((item) => (
                    <a href={item.ad} key={item.title}>
                      <div
                        style={{
                          display: "inline-block",
                          boxSizing: "border-box",
                          width: `${100 / this.state.banner.length}%`,
                          height: "100%",
                          background: `url(${item.link}) center center /cover`,
                          backgroundRepeat: "no-repeat",
                          overflow: "hidden",
                        }}
                      >
                        <img
                          src={item.link}
                          alt={item.title}
                          style={{
                            width: "90%",
                            transform: "translate(0,-5%)",
                          }}
                          // 鼠标放置在中央图片（非按钮处）时清除计时器or离开时计时器启动
                          onMouseOver={() => {
                            this.bannerIntervalOut();
                          }}
                          onMouseOut={() => {
                            this.bannerIntervalNew();
                          }}
                        />
                      </div>
                    </a>
                  ))}
                </div>
              </div>
            </div>
            {/* 公告栏板块 */}
            <div
              style={{
                position: "relative",
                boxSizing: "border-box",
                height: "26px",
                width: "100%",
                marginBottom: "5px",
                backgroundColor: "white",
                borderRadius: "3px",
                boxShadow: "2px 2px 1px rgba(0,0,0,.8)",
                overflowY: "hidden",
              }}
              // 鼠标放置在文字时清除计时器or离开时计时器启动
              onMouseOver={() => {
                this.warningIntervalOut();
              }}
              onMouseOut={() => {
                this.warningIntervalNew();
              }}
            >
              <div
                style={{
                  height: `${this.state.warning.length * 26}px`,
                  position: "absolute",
                  transform: `translate(0,${this.state.warningY}px)`,
                  transition: ".7s",
                }}
              >
                {this.state.warning.map((item) => (
                  <div
                    key={item.title}
                    style={{
                      height: "26px",
                      lineHeight: "26px",
                      textAlign: "left",
                      boxSizing: "border-box",
                      paddingLeft: "7px",
                    }}
                  >
                    <a
                      href={item.link}
                      style={{ textDecoration: "none", color: "red" }}
                    >
                      {item.title}
                    </a>
                  </div>
                ))}
              </div>
            </div>
            {/* 发表帖子板块 */}
            <div
              style={{
                width: "100%",
                height: "200px",
                boxSizing: "border-box",
                marginBottom: "8px",
                borderLeft: "1px solid black",
                borderRight: "2px solid black",
                borderRadius: "8px",
              }}
            >
              <div
                style={{
                  position: "relative",
                  height: "28px",
                  lineHeight: "28px",
                  borderBottom: "1px solid black",
                  borderRadius: "8px",
                }}
              >
                此处应该添加上发表帖子需要使用的插件，例如表情等等
                <button
                  style={{
                    position: "absolute",
                    top: "3px",
                    right: "15px",
                    width: "60px",
                    height: "20px",
                    lineHeight: "10px",
                    fontSize: "16px",
                    color: "red",
                  }}
                >
                  POST
                </button>
              </div>
              <div style={{ height: "172px", boxSizing: "border-box" }}>
                <textarea
                  name="invitationPost"
                  value={this.state.invitationPost}
                  onChange={this.handleForm}
                  style={{
                    width: "100%",
                    height: "100%",
                    margin: "0",
                    padding: "4px",
                    boxSizing: "border-box",
                    border: "0",
                    resize: "none",
                    outline: "none",
                    fontSize: "15px",
                    borderRadius: "8px",
                  }}
                ></textarea>
              </div>
            </div>
            {/* 帖子渲染板块 */}
            {this.state.invitation.map((item) => (
              <li key={item.postUid} style={{ listStyle: "none" }}>
                <InvitationList
                  photo={item.photo}
                  petName={item.petName}
                  postDate={item.postDate}
                  title={item.title}
                  summary={item.summary}
                  numRepost={item.numRepost}
                  numComment={item.numComment}
                  numLikes={item.numLikes}
                  userHome={item.userHome}
                  invitationHome={item.invitationHome}
                />
              </li>
            ))}
            {/* 中央内容板块结束 */}
          </div>
          {/* 此处网页内容结束 */}
        </div>
      </div>
    );
  }
}

export default App;
