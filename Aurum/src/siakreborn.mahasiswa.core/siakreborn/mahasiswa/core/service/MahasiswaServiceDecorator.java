package siakreborn.mahasiswa.core;

import java.util.*;

public abstract class MahasiswaServiceDecorator extends MahasiswaServiceComponent {
  protected MahasiswaServiceComponent record;

  public MahasiswaServiceDecorator(MahasiswaServiceComponent record) {
    this.record = record;
  }

  public List<Mahasiswa> saveMahasiswa(HashMap<String, Object> body) {
    return record.saveMahasiswa(body);
  }

  public Mahasiswa updateMahasiswa(HashMap<String, Object> body) {
    return record.updateMahasiswa(body);
  }

  public Mahasiswa getMahasiswa(UUID id) {
    return record.getMahasiswa(id);
  }

  public Mahasiswa getMahasiswaByEmail(String email) {
    return record.getMahasiswaByEmail(email);
  }

  public List<Mahasiswa> getAllMahasiswa() {
    return record.getAllMahasiswa();
  }

  public List<Mahasiswa> deleteMahasiswa(UUID id) {
    return record.deleteMahasiswa(id);
  }

  public List<HashMap<String, Object>> transformMahasiswaListToHashMap(List<Mahasiswa> mahasiswaList) {
    return record.transformMahasiswaListToHashMap(mahasiswaList);
  }
}
