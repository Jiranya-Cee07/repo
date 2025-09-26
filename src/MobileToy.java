import com.sun.j3d.utils.behaviors.mouse.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.*;
import javax.vecmath.*;

public class MobileToy extends javax.swing.JFrame {

    private SimpleUniverse univ = null;
    private BranchGroup scene = null;

    public MobileToy() {
        initComponents();
        Canvas3D c = createUniverse();
        drawingPanel.add(c, java.awt.BorderLayout.CENTER);
        scene = createSceneGraph();
        univ.addBranchGraph(scene);
    }

    private Canvas3D createUniverse() {
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D c = new Canvas3D(config); // step 1
        univ = new SimpleUniverse(c); // step 2
        univ.getViewingPlatform().setNominalViewingTransform(); // step 2a มุมสายตา 0,0,2.41
        univ.getViewer().getView().setMinimumFrameCycleTime(5); // วาดใหม่อย่างน้อย 5 มิลิวินาที/ครั้ง
        return c;
    }

    public BranchGroup createSceneGraph() {
        BranchGroup objRoot = new BranchGroup();
        TransformGroup tgAll = createTGTrans(new Vector3f());
        TransformGroup tgBase2 = createTGTrans(new Vector3f(0, -0.4f, 0));
        TransformGroup tgRotation = createTGTrans(new Vector3f());

        // การกำหนดค่าของสี
        Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
        Color3f blue = new Color3f(0.0f,0.0f,1.0f); //สีน้ำเงิน
        Color3f Pink2 = new Color3f(1f,0.78f,0.8f);//ชมอ่อนมาก
        
        Appearance appearBl2 = createMatAppear(true, blue, white, 64,
                                ColoringAttributes.SHADE_GOURAUD);
        appearBl2.setColoringAttributes(new ColoringAttributes
                                      (blue, ColoringAttributes.SHADE_GOURAUD));
        
        objRoot.addChild(tgAll);
        
        tgAll.addChild(tgRotation);
        
        tgAll.addChild(tgBase2);
        
        tgRotation.addChild(new Rotation());
        
        tgBase2.addChild(new Cylinder(0.5f, 0.05f, appearBl2));  

        //Animation(การหมุน)
        BoundingSphere bs = new BoundingSphere(new Point3d(0, 0, 0), 1000);
        //1.กำหนด Capability ที่เหมาะสมกับTGที่ต้องการ
        tgRotation.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        //2.สร้างวัตถุของคลาส Alpha พร้อมทั้งกำหนดจำนวนรอบ และเวลาการทำงานของแต่ละรอบ(มิลลิวินาที)
        Alpha alpha = new Alpha(-1, 5000);//หมุนสามรอบ รอบละ 3 วินาทีและค่า -1 คือหมุนไม่รู้จบ
        //3.สร้างวัตถุคลาส Interpolator(ที่เกี่ยวข้อง) และอ้างอิงวัตถุของคลาส Alpha และ TG ที่กำหนดในข้อ 2 และ 1
        RotationInterpolator rotInt = new RotationInterpolator(alpha, tgRotation);
        //4.กำหนดบริเวณการทำงานของวัตถุคลาส Interpolator
        rotInt.setSchedulingBounds(bs);
        //5.ใส่วัตถุของ Interpolator ใน Scene graph
        objRoot.addChild(rotInt);

        // Mouse Behavior setup as before...
        tgAll.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        tgAll.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        MouseRotate mr = new MouseRotate(); // Step 2
        mr.setTransformGroup(tgAll); // Step 3
        mr.setSchedulingBounds(bs); // Step 4
        objRoot.addChild(mr); // Step 5

        MouseTranslate mt = new MouseTranslate();
        mt.setTransformGroup(tgAll);
        mt.setSchedulingBounds(bs);
        objRoot.addChild(mt);

        MouseZoom mz = new MouseZoom();
        mz.setTransformGroup(tgAll);
        mz.setSchedulingBounds(bs);
        objRoot.addChild(mz);

        // Light setup...
        AmbientLight lightA = new AmbientLight();
        lightA.setInfluencingBounds(bs);
        objRoot.addChild(lightA);

        DirectionalLight lightD = new DirectionalLight();
        lightD.setInfluencingBounds(bs);
        objRoot.addChild(lightD);

        Background background = new Background();
        background.setColor(Pink2); // ให้เป็นสีฟ้า
        background.setApplicationBounds(new BoundingSphere()); // กำหนดขอบเขต
        objRoot.addChild(background);

        objRoot.compile(); // Finalize the scene graph
        return objRoot;
    }

    private Appearance createMatAppear(boolean lightOn, Color3f dColor,
                                        Color3f sColor, int shine, int shade) {
        Appearance appear = new Appearance();
        if (lightOn) {
            Material mat = new Material();
            mat.setDiffuseColor(dColor); // สีวัตถุ แสงแบบกระจาย
            mat.setSpecularColor(sColor); // จุดที่แสงตกกระทบ
            mat.setShininess(shine); // วงขอบเขตที่แสงตกกระทบ
            appear.setMaterial(mat);
        }
        appear.setColoringAttributes(new ColoringAttributes(dColor, shade));
        return appear;
    }

    private TransformGroup createTGTrans(Vector3f t) { // สำหรับการเคลื่อนที่
        Transform3D t3d1 = new Transform3D();
        t3d1.setTranslation(t);
        TransformGroup tg = new TransformGroup(t3d1);
        return tg;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        drawingPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        drawingPanel.setPreferredSize(new java.awt.Dimension(500, 500));
        drawingPanel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(drawingPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MobileToy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MobileToy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MobileToy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MobileToy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MobileToy().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JPanel drawingPanel;
    // End of variables declaration                   
}

