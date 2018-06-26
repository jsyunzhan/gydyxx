package domain.shiro.controller;

import domain.shiro.entity.JsonResponseVO;
import domain.shiro.entity.ResourceEntity;
import domain.shiro.service.UserSecurityService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/security")
public class UserSecurityController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserSecurityController.class);

    final private UserSecurityService userSecurityService;

    @Autowired
    public UserSecurityController(UserSecurityService userSecurityService){
        this.userSecurityService = userSecurityService;
    }

    /**
     * 去登陆页
     * @return ModelAndView
     */
    @RequestMapping(value = "/movetologin")
    public ModelAndView moveToLogin(){
        return new ModelAndView("login");
    }

    /**
     * 去主页
     * @return ModelAndView
     */
    @RequestMapping(value = "/home")
    public ModelAndView moveToHome(){

        //角色id
        final Long roleId= getRoleId();
        //角色名称
        final String roleName = getRoleName();

        final ModelAndView mv = new ModelAndView("home");
        mv.addObject("roleId",roleId);
        mv.addObject("roleName",roleName);

        return mv;
    }

    /**
     * 用户登录
     * @return Boolean
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public JsonResponseVO userLogin(@RequestParam("loginName") String loginName,
                                    @RequestParam("password") String password){


        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("登录认证,loginName:{}", loginName);
        }


        //登录信息验证
        final Subject securitySubject = SecurityUtils.getSubject();
        final JsonResponseVO result = new JsonResponseVO(Boolean.FALSE);

        try {

            final UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
            securitySubject.login(token);
            result.setSuccess(Boolean.TRUE);
            // rememberme
            token.setRememberMe(true);
        }   catch (UnknownAccountException ex) {

            result.setReason("账号不存在");
        } catch (IncorrectCredentialsException ex) {

            result.setReason("密码错误");
        } catch (DisabledAccountException ex) {

            result.setReason("账号不可用");
        } catch (ExcessiveAttemptsException ex) {

            result.setReason("登录失败多次，账户锁定");
        } catch (AuthenticationException ex) {

            result.setReason("其他错误");
        } catch (Exception ex) {
            System.out.println("2");
        }

        return result;
    }

    /**
     * 获取资源
     * @param roleId 角色ID
     * @return List<ResourceEntity> 资源信息
     */
    @RequestMapping(value = "/resources/{roleId}")
    @ResponseBody
    public List<ResourceEntity> getResourceInfoByRoleId(@PathVariable("roleId") Long roleId){

        return userSecurityService.getResourceInfoByRoleId(roleId);
    }
}
