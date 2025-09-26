/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Win10
 *
 * **/


import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Rotation extends BranchGroup {
    
    public Rotation(){
      Color3f yellow1= new Color3f(1.0f,0.8f,0.0f);
        createSceneGraph(yellow1);
    }
        public Rotation(Color3f c){
        createSceneGraph(c);
    }
    public void createSceneGraph(Color3f c){
        BranchGroup objRoot = new BranchGroup();
        TransformGroup tgRotation = createTGRotation();
        
        //เสากลาง
        TransformGroup tgPole = createTGTrans(new Vector3f());
        
        //ส่วนตรงยอดเสา
        TransformGroup tgTop1 = createTGTrans(new Vector3f(0, 0.35f, 0));
        
        //แขนที่ยืนออกมาทั้ง 4 ด้าน
        TransformGroup tgArm1_2 = createTGArm1_2();
        TransformGroup tgArm3_4 = createTGArm3_4();
        
        //วงกลมตรงปลายแขนทั้ง 4 ด้าน
        TransformGroup tgCircle1 = createTGTrans(new Vector3f(-0.45f, 0.35f, 0));
        TransformGroup tgCircle2 = createTGTrans(new Vector3f(0.45f, 0.35f, 0));
        TransformGroup tgCircle3 = createTGTrans(new Vector3f(0, 0.35f, 0.45f));
        TransformGroup tgCircle4 = createTGTrans(new Vector3f(0, 0.35f, -0.45f));
        
        //เชือกห้อยทั้ง 4 ด้าน
        TransformGroup tgRope1 = createTGTrans(new Vector3f(0, 0.18f, -0.45f));
        TransformGroup tgRope2 = createTGTrans(new Vector3f(0, 0.18f, 0.45f));
        TransformGroup tgRope3 = createTGTrans(new Vector3f(0.45f, 0.18f, 0));
        TransformGroup tgRope4 = createTGTrans(new Vector3f(-0.45f, 0.18f, 0));
        
        //เกสรดอกไม้
        TransformGroup tgPollen = createTGTrans(new Vector3f(0, -0.05f, -0.45f));
        
        //กลีบดอกไม้
        TransformGroup tgPetals1 = createTGTrans(new Vector3f(0, 0, -0.45f));
        TransformGroup tgPetals2 = createTGTrans(new Vector3f(-0.05f, -0.042f, -0.45f));
        TransformGroup tgPetals3 = createTGTrans(new Vector3f(0.05f, -0.042f, -0.45f));
        TransformGroup tgPetals4 = createTGTrans(new Vector3f(0.03f, -0.09f, -0.45f));
        TransformGroup tgPetals5 = createTGTrans(new Vector3f(-0.03f, -0.09f, -0.45f));
        
        //ดาว
        TransformGroup tgStar1 = createTGTrans(new Vector3f(0, 0, 0.45f));
        TransformGroup tgStar2 = createTGStar2();
        TransformGroup tgStar3 = createTGStar3();
        TransformGroup tgStar4 = createTGStar4();
        TransformGroup tgStar5 = createTGStar5();
        TransformGroup tgStar6 = createTGTrans(new Vector3f(0, -0.051f, 0.45f));
        
        //ก้อนเมฆ
        TransformGroup tgCloud1 = createTGTrans(new Vector3f(0.45f, -0.035f, 0));
        TransformGroup tgCloud2 = createTGTrans(new Vector3f(0.38f, -0.04f, 0));
        TransformGroup tgCloud3 = createTGTrans(new Vector3f(0.52f, -0.04f, 0));
        TransformGroup tgCloud4 = createTGTrans(new Vector3f(0.58f, -0.038f, 0));
        
        //กระดิ่ง
        TransformGroup tgHang = createTGTrans(new Vector3f(-0.45f, 0.05f, 0));
        TransformGroup tgBell1 = createTGTrans(new Vector3f(-0.45f, -0.03f, 0));
        TransformGroup tgBell2 = createTGTrans(new Vector3f(-0.45f, -0.13f, 0));
        //ฐานที่ 1
        TransformGroup tgBase1 = createTGTrans(new Vector3f(0, -0.35f, 0));

      
        //การกำหนดค่าของสี
        Color3f white = new Color3f(1.0f,1.0f,1.0f);
        Color3f blue = new Color3f(0.0f,0.0f,1.0f); //สีน้ำเงิน
        Color3f yellow1= new Color3f(1.0f,0.8f,0.0f);		
        Color3f yellow2 = new Color3f(0.9f,0.75f,0.5f);//สีเนื้อหรือเหลืองอ่อน
        Color3f Blue1 =  new Color3f(0.4f, 0.6f, 0.8f);//ฟ้าอ่อน
        Color3f Pink3 = new Color3f(1f,0.6f,0.7f);
        Color3f Pink4 = new Color3f(1f,0.2f,0.4f);
        
        
        Appearance appearPk = createMatAppear(true,Pink4,white,64,
                                              ColoringAttributes.SHADE_FLAT);
        appearPk.setColoringAttributes(new ColoringAttributes
                                      (Pink4, ColoringAttributes.SHADE_GOURAUD));
        Appearance appearYe2 = createMatAppear(true,yellow2,white,64,
                                              ColoringAttributes.SHADE_FLAT);
        appearYe2.setColoringAttributes(new ColoringAttributes
                                      (yellow2, ColoringAttributes.SHADE_GOURAUD));
        Appearance appearYe1 = createMatAppear(true,yellow1,white,64,
                                              ColoringAttributes.SHADE_FLAT);
        appearYe1.setColoringAttributes(new ColoringAttributes
                                      (yellow1, ColoringAttributes.SHADE_GOURAUD));
        Appearance appearBl1 = createMatAppear(true,Blue1,white,64,
                                              ColoringAttributes.SHADE_FLAT);
        appearBl1.setColoringAttributes(new ColoringAttributes
                                      (Blue1, ColoringAttributes.SHADE_GOURAUD));
        Appearance appearPk3 = createMatAppear(true,Pink3,white,64,
                                              ColoringAttributes.SHADE_FLAT);
        appearPk3.setColoringAttributes(new ColoringAttributes
                                      (Pink3, ColoringAttributes.SHADE_GOURAUD));
         Appearance appearW = createMatAppear(true,white,white,64,
                                              ColoringAttributes.SHADE_FLAT);
        appearW.setColoringAttributes(new ColoringAttributes
                                      (white, ColoringAttributes.SHADE_GOURAUD));
        Appearance appearBl2 = createMatAppear(true,blue,white,64,
                                              ColoringAttributes.SHADE_FLAT);
        appearBl2.setColoringAttributes(new ColoringAttributes
                                      (blue, ColoringAttributes.SHADE_GOURAUD));
        
        
        
        
        
        this.addChild(tgRotation);
        
        tgRotation.addChild(tgPole);
        
        tgRotation.addChild(tgTop1);
        
        tgRotation.addChild(tgArm1_2);
        tgRotation.addChild(tgArm3_4);
        
        tgRotation.addChild(tgCircle1);
        tgRotation.addChild(tgCircle2);
        tgRotation.addChild(tgCircle3);
        tgRotation.addChild(tgCircle4);
        
        tgRotation.addChild(tgRope1);
        tgRotation.addChild(tgRope2);
        tgRotation.addChild(tgRope3);
        tgRotation.addChild(tgRope4);
        
        tgRotation.addChild(tgPollen);
        
        tgRotation.addChild(tgPetals1);
        tgRotation.addChild(tgPetals2);
        tgRotation.addChild(tgPetals3);
        tgRotation.addChild(tgPetals4);
        tgRotation.addChild(tgPetals5);
        
        tgRotation.addChild(tgStar1);
        tgRotation.addChild(tgStar2);
        tgRotation.addChild(tgStar3);
        tgRotation.addChild(tgStar4);
        tgRotation.addChild(tgStar5);
        tgRotation.addChild(tgStar6);
        
        tgRotation.addChild(tgCloud1);
        tgRotation.addChild(tgCloud2);
        tgRotation.addChild(tgCloud3);
        tgRotation.addChild(tgCloud4);
        
        tgRotation.addChild(tgHang);
        tgRotation.addChild(tgBell1);
        tgRotation.addChild(tgBell2);
        
        tgRotation.addChild(tgBase1);
        
        
        
        
       tgPole.addChild(new Cylinder(0.02f, 0.7f, appearW));
       
       tgTop1.addChild(new Cylinder(0.2f, 0.07f, appearBl2));
       
       tgArm1_2.addChild(new Cylinder(0.015f, 0.9f, appearW));
       tgArm3_4.addChild(new Cylinder(0.015f, 0.9f, appearW));
       
       tgCircle1.addChild(new Sphere(0.04f, appearBl2));
       tgCircle2.addChild(new Sphere(0.04f, appearBl2));
       tgCircle3.addChild(new Sphere(0.04f, appearBl2));
       tgCircle4.addChild(new Sphere(0.04f, appearBl2));
       
       tgRope1.addChild(new Cylinder(0.01f, 0.3f, appearW));
       tgRope2.addChild(new Cylinder(0.01f, 0.3f, appearW));
       tgRope3.addChild(new Cylinder(0.01f, 0.3f, appearW));
       tgRope4.addChild(new Cylinder(0.01f, 0.3f, appearW));
       
       tgPollen.addChild(new Sphere(0.05f, appearPk3));
       
       tgPetals1.addChild(new Sphere(0.05f, appearPk));
       tgPetals2.addChild(new Sphere(0.05f, appearPk));
       tgPetals3.addChild(new Sphere(0.05f, appearPk));
       tgPetals4.addChild(new Sphere(0.05f, appearPk));
       tgPetals5.addChild(new Sphere(0.05f, appearPk));
       
       tgStar1.addChild(new Cone(0.05f,0.12f, appearYe1));
       tgStar2.addChild(new Cone(0.05f,0.12f, appearYe1));
       tgStar3.addChild(new Cone(0.05f,0.12f, appearYe1));
       tgStar4.addChild(new Cone(0.05f,0.12f, appearYe1));
       tgStar5.addChild(new Cone(0.05f,0.12f, appearYe1));
       tgStar6.addChild(new Sphere(0.055f, appearYe1));
       
       tgCloud1.addChild(new Sphere(0.08f, appearBl1));
       tgCloud2.addChild(new Sphere(0.068f, appearBl1));
       tgCloud3.addChild(new Sphere(0.068f, appearBl1));
       tgCloud4.addChild(new Sphere(0.05f, appearBl1));
       
       tgHang.addChild(new Sphere(0.02f, appearYe2));
       tgBell1.addChild(new Cone(0.08f,0.18f, appearYe2));
       tgBell2.addChild(new Sphere(0.03f, appearYe2));
       
       tgBase1.addChild(new Cylinder(0.2f, 0.07f, appearBl2)); 
        
        this.compile();//step 4
    }
    
    
    private Appearance createMatAppear(boolean lightOn, Color3f dColor,
                                       Color3f sColor,int shine,int shade){
        Appearance appear = new Appearance();
        if (lightOn) {
            Material mat = new Material();
            mat.setDiffuseColor(dColor);//สีวัตถุ แสงแบบกระจาย
            mat.setSpecularColor(sColor);//จุดที่แสงตกกระทบ
            mat.setShininess(shine);//วงขอบเขตที่แสงตกกระทบ
            appear.setMaterial(mat);
        }
        appear.setColoringAttributes(new ColoringAttributes(dColor,shade));
        return appear;
    }
    private TransformGroup createTGTrans(Vector3f t){//สำหรับการเคลื่อนที่
        Transform3D t3d1 = new Transform3D();
        t3d1.setTranslation(t);
        TransformGroup tg = new TransformGroup(t3d1);
        return tg;
    }
    private TransformGroup createTGRotation(){
        Transform3D t3d1 = new Transform3D();
        TransformGroup tg = new TransformGroup(t3d1);
        return tg;
    }
    private TransformGroup createTGArm1_2(){
        Transform3D t3d1 = new Transform3D();
        t3d1.rotX(Math.PI/2);
        t3d1.setTranslation(new Vector3f(0,0.35f,0));
        TransformGroup tg = new TransformGroup(t3d1);
        return tg;
    }
    private TransformGroup createTGArm3_4(){
        Transform3D t3d1 = new Transform3D();
        t3d1.rotZ(Math.PI/2);
        t3d1.setTranslation(new Vector3f(0,0.35f,0));
        TransformGroup tg = new TransformGroup(t3d1);
        return tg;
    }
    private TransformGroup createTGStar2(){
        Transform3D t3d1 = new Transform3D();
        t3d1.rotZ(Math.PI/2);
        t3d1.setTranslation(new Vector3f(-0.06f, -0.045f, 0.45f));
        TransformGroup tg = new TransformGroup(t3d1);
        return tg;
    }
    private TransformGroup createTGStar3(){
        Transform3D t3d1 = new Transform3D();
        t3d1.rotZ(Math.PI/-2);
        t3d1.setTranslation(new Vector3f(0.06f, -0.045f, 0.45f));
        TransformGroup tg = new TransformGroup(t3d1);
        return tg;
    }
    private TransformGroup createTGStar4(){
        Transform3D t3d1 = new Transform3D();
        t3d1.rotZ(Math.PI/1.3);
        t3d1.setTranslation(new Vector3f(-0.035f, -0.1f, 0.45f));
        TransformGroup tg = new TransformGroup(t3d1);
        return tg;
    }
    private TransformGroup createTGStar5(){
        Transform3D t3d1 = new Transform3D();
        t3d1.rotZ(Math.PI/-1.3);
        t3d1.setTranslation(new Vector3f(0.035f, -0.1f, 0.45f));
        TransformGroup tg = new TransformGroup(t3d1);
        return tg;
    }
}
