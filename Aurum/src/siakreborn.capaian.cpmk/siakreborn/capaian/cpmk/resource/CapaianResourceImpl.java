package siakreborn.capaian.cpmk;

import vmj.routing.route.VMJExchange;
import vmj.routing.route.Route;
import vmj.auth.annotations.Restricted;

import siakreborn.capaian.core.Capaian;
import siakreborn.capaian.core.CapaianImpl;
import siakreborn.capaian.core.CapaianResourceDecorator;
import siakreborn.capaian.core.CapaianResourceComponent;
import siakreborn.capaian.core.CapaianService;
import siakreborn.capaian.core.CapaianServiceComponent;

import java.util.*;

public class CapaianResourceImpl extends CapaianResourceDecorator {
  private CapaianService capaianService;

  public CapaianResourceImpl(CapaianResourceComponent recordController, CapaianServiceComponent recordService) {
    super(recordController);
    this.capaianService = new CapaianServiceImpl(recordService);
  }

  @Restricted(permissionName = "SaveCPMK")
  @Route(url = "call/cpmk/save")
  public List<HashMap<String, Object>> saveCapaian(VMJExchange vmjExchange) {
    if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
      return null;
    }

    List<Capaian> capaianList = capaianService.saveCapaian((HashMap<String, Object>) vmjExchange.getPayload());
    return capaianService.transformCapaianListToHashMap(capaianList);
  }

  @Restricted(permissionName = "UpdateCPMK")
  @Route(url = "call/cpmk/update")
  public HashMap<String, Object> updateCapaian(VMJExchange vmjExchange) {
    Capaian capaian = capaianService.updateCapaian((HashMap<String, Object>) vmjExchange.getPayload());
    return capaian.toHashMap();
  }

  @Restricted(permissionName = "ReadCPMK")
  @Route(url = "call/cpmk/detail")
  public HashMap<String, Object> getCapaian(VMJExchange vmjExchange) {
    String idStr = vmjExchange.getGETParam("id");
    UUID id = UUID.fromString(idStr);

    Capaian capaian = capaianService.getCapaian(id);
    return capaian.toHashMap();
  }

  @Restricted(permissionName = "ReadCPMK")
  @Route(url = "call/cpmk/list")
  public List<HashMap<String, Object>> getAllCapaian(VMJExchange vmjExchange) {
    List<Capaian> capaianList = capaianService.getAllCapaian();
    return capaianService.transformCapaianListToHashMap(capaianList);
  }

  @Restricted(permissionName = "ReadCPMK")
  @Route(url = "call/cpmk/filter")
  public List<HashMap<String, Object>> filterCapaian(VMJExchange vmjExchange) {
    String by = (String) vmjExchange.getGETParam("by");

    if (by.equals("mataKuliah") || by.equals(("parentCPL"))) {
      UUID id = null;
      String idStr = (String) vmjExchange.getGETParam(by + "Id");
      if (idStr != null) {
        id = UUID.fromString(idStr);
      }

      String columnName = by.toLowerCase() + "_id";

      List<Capaian> capaianList = ((CapaianServiceImpl) capaianService).filterCapaian(columnName, id);
      return capaianService.transformCapaianListToHashMap(capaianList);
    }

    return new ArrayList<>();
  }

  @Restricted(permissionName = "DeleteCPMK")
  @Route(url = "call/cpmk/delete")
  public List<HashMap<String, Object>> deleteCapaian(VMJExchange vmjExchange) {
    if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
      return null;
    }

    String idStr = (String) vmjExchange.getRequestBodyForm("id");
    UUID id = UUID.fromString(idStr);

    List<Capaian> capaianList = capaianService.deleteCapaian(id);
    return capaianService.transformCapaianListToHashMap(capaianList);
  }

}
