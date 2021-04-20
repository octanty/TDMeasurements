package TDMeasurement.SqualeService.service;

import TDMeasurement.MaintainabilityIndexService.controller.CLOC;
import TDMeasurement.MaintainabilityIndexService.service.MIService;
import TDMeasurement.MaintainabilityIndexService.vo.Directory;
import TDMeasurement.SqualeService.controller.MetricResults;
import TDMeasurement.SqualeService.entity.Squale;
import TDMeasurement.SqualeService.repository.SqualeRepository;
import gr.spinellis.ckjm.CkjmOutputHandler;
import gr.spinellis.ckjm.ClassMetrics;
import gr.spinellis.ckjm.MetricsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class SqualeService {

    @Autowired
    private RestTemplate restTemplates;

    @Autowired
    private SqualeRepository squaleRepository;

    public List<Squale> calculate() throws IOException {

        int dit = 0;
        int ca = 0;
        int lcom = 0;
        int rfc = 0;
        int ce = 0;
        int sloc = 0;
        int cloc = 0;
        int cyc = 0;


        ResponseEntity<Directory[]> response = restTemplates.getForEntity(
                "http://localhost:8004/directories/listDirectoryInDB", Directory[].class);

        List<Directory> listDirectory =  Arrays.asList(response.getBody());
        Squale sq = new Squale();
        List<Squale> list = new ArrayList<>();

        final AtomicReference<ClassMetrics> ref = new AtomicReference<>();
        for(int n=0; n<listDirectory.size(); n++) {
            CkjmOutputHandler outputHandler = new CkjmOutputHandler() {
                @Override
                public void handleClass(String s, ClassMetrics classMetrics) {
                    System.out.println("name: " + s + ", WMC: " + classMetrics.getWmc());
                }
            };
            List<String> listJavaFiles = MIService.getJavaFiles(listDirectory.get(n).getPath());
            for (int i = 0; i < listJavaFiles.size(); i++) {
                File f = new File(listJavaFiles.get(i));
                //TODO: fix this function, create unit testing for this function
             /*   MetricsFilter mf = new MetricsFilter();
                mf.runMetrics(new String[] { f.getAbsolutePath() },outputHandler, true);
                dit += ref.get().getDit();
                ca += ref.get().getCa();
                rfc += ref.get().getRfc();
                lcom += ref.get().getLcom();
                ce += ref.get().getCe(); */
                MetricResults mr = new MetricResults();
                sloc += mr.getLOC(listJavaFiles.get(i));
                CLOC commentLOC = new CLOC();
                cloc += commentLOC.getCommentNumber(listJavaFiles.get(i));
                cyc += mr.getCyC(listJavaFiles.get(i));
            }
            sq = insertIMToDB(dit,ca,rfc,ce,lcom,sloc, cloc, cyc, listDirectory.get(n).toString());
            list.add(sq);
        }
        return list;
    }

    public Squale insertIMToDB(int dit, int ca, int rfc, int ce, int lcom, int sloc, int cloc, int cyc, String dir) throws IOException {
        int ncloc = sloc-cloc;
        MetricResults mr = new MetricResults();
        Squale sq = new Squale();
        sq.setDirName(dir);
    /*    int imID = mr.getIMInheritanceDept(dit);
        sq.setimID(imID);
        double imEC = mr.getIMEfferentCoupling(ce);
        sq.setimEC(imEC);
        double imAC = mr.getIMAfferentCoupling(ca);
        sq.setimAC(imAC);
        double imSAK = mr.getIMSwissArmyKnife(lcom,ca,rfc);
        sq.setimSAK(imSAK);
        double imCC = mr.getIMClassCohession(lcom);
        sq.setimCC(imCC);
        //TODO: fix numDuplication, learn again pmd library
        double imDup = mr.getIMCopyPaste(0,sloc);
        sq.setimDup(imDup); */

        double imMS = mr.getIMMethodSize(sloc);
        sq.setimMS(imMS);
        double imDistance = mr.getIMDistance(dir);
        sq.setimDistance(imDistance);
        int imDC = mr.getIMDependencyCycle(dir);
        sq.setimDC(imDC);
        double imSC = mr.getIMSpaghetyCode(sloc, cyc);
        sq.setimSC(imSC);
        double imSOCRate =(mr.getIMSOCRate(ncloc,sloc,cyc));
        sq.setimSOCRate(imSOCRate);

     /*   sq.setWimID(mr.getWeightingIM(mr.getWeightID(),imID));
        sq.setWimEC(mr.getWeightingIM(mr.getWeightEC(),imEC));
        sq.setWimAC(mr.getWeightingIM(mr.getWeightAC(),imAC));
        sq.setWimSAK(mr.getWeightingIM(mr.getWeightAC(),imSAK));
        sq.setWimCC(mr.getWeightingIM(mr.getWeightCC(),imCC)); */
        sq.setWimMS(mr.getWeightingIM(mr.getWeightMS(),imMS));
        sq.setWimDup(mr.getWeightingIM(mr.getWeightCP(),imDup));
        sq.setWimDistance(mr.getWeightingIM(mr.getWeightDistance(),0));
        sq.setWimSC(mr.getWeightingIM(mr.getWeightSC(),imSC));
         sq.setWimSOCRate(mr.getWeightingIM(mr.getWeightSOC(),imSOCRate));
        int nom = mr.getNOM(dir);
        sq.setimNOM(mr.getIMNOM(nom,cyc));
        return squaleRepository.save(sq);
    }


    public List<Squale> getListResult(){
        return squaleRepository.findAll();
    }

    //TODO: create method insertglobalMarkToDB(), see from paper smr1588


}
