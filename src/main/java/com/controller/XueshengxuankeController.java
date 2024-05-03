
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 学生选课
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/xueshengxuanke")
public class XueshengxuankeController {
    private static final Logger logger = LoggerFactory.getLogger(XueshengxuankeController.class);

    private static final String TABLE_NAME = "xueshengxuanke";

    @Autowired
    private XueshengxuankeService xueshengxuankeService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表非注册的service
    @Autowired
    private KechengService kechengService;
    //注册表service
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private LaoshiService laoshiService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("学生".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("老师".equals(role))
            params.put("laoshiId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = xueshengxuankeService.queryPage(params);

        //字典表数据转换
        List<XueshengxuankeView> list =(List<XueshengxuankeView>)page.getList();
        for(XueshengxuankeView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        XueshengxuankeEntity xueshengxuanke = xueshengxuankeService.selectById(id);
        if(xueshengxuanke !=null){
            //entity转view
            XueshengxuankeView view = new XueshengxuankeView();
            BeanUtils.copyProperties( xueshengxuanke , view );//把实体数据重构到view中
            //级联表 学生
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(xueshengxuanke.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //级联表 课程
            //级联表
            KechengEntity kecheng = kechengService.selectById(xueshengxuanke.getKechengId());
            if(kecheng != null){
            BeanUtils.copyProperties( kecheng , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setKechengId(kecheng.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody XueshengxuankeEntity xueshengxuanke, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,xueshengxuanke:{}",this.getClass().getName(),xueshengxuanke.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("学生".equals(role))
            xueshengxuanke.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<XueshengxuankeEntity> queryWrapper = new EntityWrapper<XueshengxuankeEntity>()
            .eq("yonghu_id", xueshengxuanke.getYonghuId())
            .eq("kecheng_id", xueshengxuanke.getKechengId())
            .in("xueshengxuanke_yesno_types", new Integer[]{1,2})
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XueshengxuankeEntity xueshengxuankeEntity = xueshengxuankeService.selectOne(queryWrapper);
        if(xueshengxuankeEntity==null){
            xueshengxuanke.setInsertTime(new Date());
            xueshengxuanke.setXueshengxuankeYesnoTypes(1);
            xueshengxuanke.setCreateTime(new Date());
            xueshengxuankeService.insert(xueshengxuanke);
            return R.ok();
        }else {
            if(xueshengxuankeEntity.getXueshengxuankeYesnoTypes()==1){
                return R.error(511,"该学生已经选了此课了,请等待审核");
            }else{
                return R.error(511,"该学生已经通过了此选课,不能重复选课");
            }
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody XueshengxuankeEntity xueshengxuanke, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,xueshengxuanke:{}",this.getClass().getName(),xueshengxuanke.toString());
        XueshengxuankeEntity oldXueshengxuankeEntity = xueshengxuankeService.selectById(xueshengxuanke.getId());//查询原先数据

            xueshengxuankeService.updateById(xueshengxuanke);//根据id更新
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody XueshengxuankeEntity xueshengxuankeEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,xueshengxuankeEntity:{}",this.getClass().getName(),xueshengxuankeEntity.toString());

        XueshengxuankeEntity oldXueshengxuanke = xueshengxuankeService.selectById(xueshengxuankeEntity.getId());//查询原先数据

//        if(xueshengxuankeEntity.getXueshengxuankeYesnoTypes() == 2){//通过
//            xueshengxuankeEntity.setXueshengxuankeTypes();
//        }else if(xueshengxuankeEntity.getXueshengxuankeYesnoTypes() == 3){//拒绝
//            xueshengxuankeEntity.setXueshengxuankeTypes();
//        }
        xueshengxuankeEntity.setXueshengxuankeShenheTime(new Date());//审核时间
        xueshengxuankeService.updateById(xueshengxuankeEntity);//审核

        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<XueshengxuankeEntity> oldXueshengxuankeList =xueshengxuankeService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        xueshengxuankeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<XueshengxuankeEntity> xueshengxuankeList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            XueshengxuankeEntity xueshengxuankeEntity = new XueshengxuankeEntity();
//                            xueshengxuankeEntity.setYonghuId(Integer.valueOf(data.get(0)));   //学生 要改的
//                            xueshengxuankeEntity.setKechengId(Integer.valueOf(data.get(0)));   //课程 要改的
//                            xueshengxuankeEntity.setInsertTime(date);//时间
//                            xueshengxuankeEntity.setXueshengxuankeYesnoTypes(Integer.valueOf(data.get(0)));   //申请状态 要改的
//                            xueshengxuankeEntity.setXueshengxuankeYesnoText(data.get(0));                    //审核意见 要改的
//                            xueshengxuankeEntity.setXueshengxuankeShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            xueshengxuankeEntity.setCreateTime(date);//时间
                            xueshengxuankeList.add(xueshengxuankeEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        xueshengxuankeService.insertBatch(xueshengxuankeList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





}
