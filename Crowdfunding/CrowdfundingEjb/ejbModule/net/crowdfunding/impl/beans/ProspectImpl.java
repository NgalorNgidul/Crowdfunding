package net.crowdfunding.impl.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import net.crowdfunding.intf.beans.IProspect;
import net.crowdfunding.intf.model.Member;
import net.crowdfunding.intf.model.Prospect;

@Stateless
@Remote(IProspect.class)
public class ProspectImpl implements IProspect {

	private List<Prospect> createProspects() {
		List<Prospect> prospects = new ArrayList<Prospect>();
		//
		Prospect prospect = new Prospect();
		prospect.setId(1);
		prospect.setTitle("Renovasi Rumah");
		prospect.setShortDescription("Bertambahnya anggota keluarga menjadikan rumah yang kami tempati perlu penambahan ruangan. Di perlukan dana untuk renovasi rumah sederhana dengan luas 36m.");
		prospect.setLocation("Tangerang Selatan");
		prospect.setProvince("Banten");
		Member member = new Member();
		member.setName("Syahrial Fandrianah");
		prospect.setOwner(member);
		prospect.setPrincipal(40000000);
		prospects.add(prospect);
		//
		prospect = new Prospect();
		prospect.setId(2);
		prospect.setTitle("Kafe Lesehan");
		prospect.setShortDescription("Bisnis kafe dengan model lesehan saat ini sedang mulai tumbuh di pinggiran kota Depok.");
		prospect.setLocation("Depok");
		prospect.setProvince("Jawa Barat");
		member = new Member();
		member.setName("Nanang Suhendro");
		prospect.setOwner(member);
		prospect.setPrincipal(15000000);
		prospects.add(prospect);
		//
		prospect = new Prospect();
		prospect.setId(3);
		prospect.setTitle("Kerajinan Kayu");
		prospect.setShortDescription("Penjualan kerajinan kayu menjadi bisnis yang tak pernah kenal waktu. Barang kerajinan dipesan langsung ke pengrajin di daerah Boyolali, dan dikerjakan dengan tangan.");
		prospect.setLocation("Jakarta Selatan");
		prospect.setProvince("DKI Jakarta");
		member = new Member();
		member.setName("Puguh Tri");
		prospect.setOwner(member);
		prospect.setPrincipal(50000000);
		prospects.add(prospect);
		//
		prospect = new Prospect();
		prospect.setId(4);
		prospect.setTitle("Florist");
		prospect.setShortDescription("Kios penjualan bunga butuh tambahan modal. Lokasi yang berdekatan dengan rumah sakit membuat usaha kami cepat berkembang.");
		prospect.setLocation("Bogor");
		prospect.setProvince("Jawa Barat");
		member = new Member();
		member.setName("Nina Sri");
		prospect.setOwner(member);
		prospect.setPrincipal(17000000);
		prospects.add(prospect);
		//
		return prospects;
	}
	
	@Override
	public Prospect get(long id) {
		List<Prospect> prospects = createProspects();
		Long lId = new Long(id);
		return prospects.get(lId.intValue()-1);
	}

	@Override
	public List<Prospect> listPopular() {
		return createProspects();
	}

	@Override
	public List<Prospect> find(String textKey) {
		return createProspects();
	}

}
